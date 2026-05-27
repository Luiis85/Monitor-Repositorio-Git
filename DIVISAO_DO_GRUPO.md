# Divisão dos arquivos do grupo — Repo Monitor

A pasta completa deve ficar na branch `main`. Cada integrante trabalha somente nos arquivos atribuídos em sua própria branch e registra seus próprios commits.

## Integrante 1 — Modelagem e Persistência
- `src/main/java/com/mackenzie/repomonitor/model/DevUser.java`
- `src/main/java/com/mackenzie/repomonitor/model/Projeto.java`
- `src/main/java/com/mackenzie/repomonitor/model/Tecnologia.java`
- `src/main/java/com/mackenzie/repomonitor/model/Favorito.java`
- `src/main/java/com/mackenzie/repomonitor/model/RegistroConsulta.java`
- `src/main/java/com/mackenzie/repomonitor/repository/UsuarioRepository.java`
- `src/main/java/com/mackenzie/repomonitor/repository/ProjetoRepository.java`
- `src/main/java/com/mackenzie/repomonitor/repository/TecnologiaRepository.java`
- `src/main/java/com/mackenzie/repomonitor/repository/FavoritoRepository.java`
- `src/main/java/com/mackenzie/repomonitor/repository/ConsultaRepository.java`
- `src/main/resources/application.properties`

## Integrante 2 — Integração com GitHub, Regra de Negócio e Tratamento de Erros
- `src/main/java/com/mackenzie/repomonitor/service/ServicoGitHub.java`
- `src/main/java/com/mackenzie/repomonitor/dto/DadosUsuarioGitHub.java`
- `src/main/java/com/mackenzie/repomonitor/dto/DadosRepositorioGitHub.java`
- `src/main/java/com/mackenzie/repomonitor/dto/RelatorioUsuario.java`
- `src/main/java/com/mackenzie/repomonitor/exception/NaoEncontradoException.java`
- `src/main/java/com/mackenzie/repomonitor/exception/TratadorDeErros.java`

## Integrante 3 — API REST, Interface Swagger e Inicialização
- `src/main/java/com/mackenzie/repomonitor/controller/ControladorUsuario.java`
- `src/main/java/com/mackenzie/repomonitor/controller/ControladorProjeto.java`
- `src/main/java/com/mackenzie/repomonitor/controller/ControladorFavorito.java`
- `src/main/java/com/mackenzie/repomonitor/controller/ControladorTecnologia.java`
- `src/main/java/com/mackenzie/repomonitor/RepoMonitorApplication.java`
- `pom.xml`

Os pacotes separados não executam sozinhos: todos dependem uns dos outros. Eles servem para deixar clara a responsabilidade individual; a execução deve ser feita no projeto completo.
