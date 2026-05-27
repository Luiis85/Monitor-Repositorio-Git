package com.mackenzie.repomonitor.service;

import com.mackenzie.repomonitor.dto.DadosRepositorioGitHub;
import com.mackenzie.repomonitor.dto.DadosUsuarioGitHub;
import com.mackenzie.repomonitor.dto.RelatorioUsuario;
import com.mackenzie.repomonitor.exception.NaoEncontradoException;
import com.mackenzie.repomonitor.model.*;
import com.mackenzie.repomonitor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ServicoGitHub {

    // RestTemplate é o objeto do Spring que faz requisições HTTP
    private final RestTemplate clienteHttp = new RestTemplate();

    // Endereço base da API do GitHub
    private static final String URL_BASE_GITHUB = "https://api.github.com";

    // O Spring injeta os repositórios automaticamente (sem precisar de "new")
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private TecnologiaRepository tecnologiaRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private FavoritoRepository favoritoRepository;
    // IMPORTAR USUÁRIO
    // Consulta o GitHub, salva o usuário e seus repositórios
    public DevUser importarUsuario(String login) {

        // Se o usuário já foi importado antes, retorna o que está no banco
        if (usuarioRepository.existsByLogin(login)) {
            return usuarioRepository.findByLogin(login).get();
        }

        // Chama a API do GitHub para buscar o perfil
        DadosUsuarioGitHub dadosDaApi = consultarPerfilNaApi(login);

        // Converte os dados da API para nossa entidade e salva no banco
        DevUser novoUsuario = new DevUser();
        novoUsuario.setLogin(dadosDaApi.getLogin());
        novoUsuario.setNome(dadosDaApi.getNome());
        novoUsuario.setUrlFoto(dadosDaApi.getUrlFoto());
        novoUsuario.setBio(dadosDaApi.getBio());
        novoUsuario.setEmpresa(dadosDaApi.getEmpresa());
        novoUsuario.setLocalizacao(dadosDaApi.getLocalizacao());
        novoUsuario.setSeguidores(dadosDaApi.getSeguidores());
        novoUsuario.setSeguindo(dadosDaApi.getSeguindo());
        novoUsuario.setTotalRepositorios(dadosDaApi.getTotalRepositorios());

        DevUser usuarioSalvo = usuarioRepository.save(novoUsuario);

        // Grava um registro de que essa consulta foi feita com sucesso
        gravarRegistroDeConsulta(usuarioSalvo, "/users/" + login, "SUCESSO");

        // Busca e salva os repositórios desse usuário
        importarRepositoriosDoUsuario(usuarioSalvo);

        return usuarioSalvo;
    }

    // CONSULTAR PERFIL NA A
    private DadosUsuarioGitHub consultarPerfilNaApi(String login) {
        try {
            String url = URL_BASE_GITHUB + "/users/" + login;

            // getForObject faz um GET HTTP e converte o JSON da resposta
            // automaticamente para o objeto DadosUsuarioGitHub
            DadosUsuarioGitHub resposta = clienteHttp.getForObject(url, DadosUsuarioGitHub.class);

            if (resposta == null) {
                throw new NaoEncontradoException("Usuário '" + login + "' não encontrado no GitHub.");
            }

            return resposta;

        } catch (HttpClientErrorException.NotFound e) {
            // O GitHub devolveu 404: o usuário não existe lá
            throw new NaoEncontradoException("Usuário '" + login + "' não existe no GitHub.");
        }
        // Erros de rede (sem conexão, timeout) sobem para o TratadorDeErros
    }

    // IMPORTAR REPOSITÓRIOS DO USUÁRIO
    private void importarRepositoriosDoUsuario(DevUser usuario) {
        try {
            String url = URL_BASE_GITHUB + "/users/" + usuario.getLogin() + "/repos?per_page=10";

            // A API devolve uma lista de repositórios — recebemos como array
            DadosRepositorioGitHub[] repositorios = clienteHttp.getForObject(url, DadosRepositorioGitHub[].class);

            if (repositorios == null) return;

            for (DadosRepositorioGitHub repoDaApi : repositorios) {
                Projeto projeto = new Projeto();
                projeto.setNome(repoDaApi.getNome());
                projeto.setDescricao(repoDaApi.getDescricao());
                projeto.setUrlGithub(repoDaApi.getUrlGithub());
                projeto.setEstrelas(repoDaApi.getEstrelas());
                projeto.setForks(repoDaApi.getForks());
                projeto.setLinguagemPrincipal(repoDaApi.getLinguagemPrincipal());
                projeto.setPrivado(repoDaApi.getPrivado() != null ? repoDaApi.getPrivado() : false);
                projeto.setDono(usuario);

                // Se o repositório tem linguagem definida, vincula a tecnologia
                if (repoDaApi.getLinguagemPrincipal() != null) {
                    Tecnologia tecnologia = buscarOuCadastrarTecnologia(repoDaApi.getLinguagemPrincipal());
                    projeto.setTecnologias(new ArrayList<>(Arrays.asList(tecnologia)));
                }

                projetoRepository.save(projeto);
            }

        } catch (Exception e) {
            // Se falhar ao importar os repos, registra o erro mas não derruba a importação do usuário
            gravarRegistroDeConsulta(usuario, "/users/" + usuario.getLogin() + "/repos", "ERRO: " + e.getMessage());
        }
    }

    private Tecnologia buscarOuCadastrarTecnologia(String nome) {
        return tecnologiaRepository.findByNome(nome)
            .orElseGet(() -> {
                Tecnologia nova = new Tecnologia();
                nova.setNome(nome);
                return tecnologiaRepository.save(nova);
            });
    }

    // GRAVAR REGISTRO DE CONSULTA (log de auditoria)
    private void gravarRegistroDeConsulta(DevUser usuario, String endereco, String resultado) {
        RegistroConsulta registro = new RegistroConsulta();
        registro.setRealizadaEm(LocalDateTime.now());
        registro.setEnderecoConsultado(endereco);
        registro.setResultado(resultado);
        registro.setUsuario(usuario);
        consultaRepository.save(registro);
    }

    // RELATÓRIO AGREGADO
    // Combina dados do banco local com dados em tempo real da API
    public RelatorioUsuario gerarRelatorio(String login) {

        // Busca o usuário no banco — lança 404 se não existir
        DevUser usuario = usuarioRepository.findByLogin(login)
            .orElseThrow(() -> new NaoEncontradoException(
                "Usuário '" + login + "' não encontrado. Importe-o primeiro via POST /api/usuarios/{login}."));

        RelatorioUsuario relatorio = new RelatorioUsuario();
        relatorio.setLogin(usuario.getLogin());
        relatorio.setNome(usuario.getNome());

        // Dados do banco local
        List<Projeto> projetos = projetoRepository.findByDonoId(usuario.getId());
        relatorio.setTotalProjetosSalvos(projetos.size());
        relatorio.setTotalFavoritos((int) favoritoRepository.count());
        relatorio.setNomeDosProjetosSalvos(projetos.stream().map(Projeto::getNome).toList());

        // Dados em tempo real da API do GitHub
        try {
            DadosUsuarioGitHub dadosAtuais = consultarPerfilNaApi(login);
            relatorio.setSeguidoresAgora(dadosAtuais.getSeguidores());
            relatorio.setRepositoriosPublicosAgora(dadosAtuais.getTotalRepositorios());
            relatorio.setBioAtual(dadosAtuais.getBio());
            relatorio.setStatusDaApi("ONLINE");

        } catch (Exception e) {
            // API fora do ar: usa os dados que já temos salvos como fallback
            relatorio.setSeguidoresAgora(usuario.getSeguidores());
            relatorio.setRepositoriosPublicosAgora(usuario.getTotalRepositorios());
            relatorio.setBioAtual(usuario.getBio());
            relatorio.setStatusDaApi("API_FORA - exibindo dados salvos em cache");
        }

        return relatorio;
    }
    // CRUD DO DEVUSER

    public List<DevUser> listarTodos() {
        return usuarioRepository.findAll();
    }

    public DevUser buscarPorId(Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Usuário com id " + id + " não encontrado."));
    }

    public DevUser atualizar(Long id, DevUser dadosNovos) {
        DevUser existente = buscarPorId(id);
        existente.setNome(dadosNovos.getNome());
        existente.setBio(dadosNovos.getBio());
        existente.setLocalizacao(dadosNovos.getLocalizacao());
        existente.setEmpresa(dadosNovos.getEmpresa());
        return usuarioRepository.save(existente);
    }

    public void deletar(Long id) {
        DevUser usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }
}
