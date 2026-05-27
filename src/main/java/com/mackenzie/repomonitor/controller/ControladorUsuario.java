package com.mackenzie.repomonitor.controller;

import com.mackenzie.repomonitor.dto.RelatorioUsuario;
import com.mackenzie.repomonitor.model.DevUser;
import com.mackenzie.repomonitor.service.ServicoGitHub;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Controller de usuários.
 *
 * Recebe as requisições HTTP, repassa para o ServicoGitHub,
 * e devolve a resposta com o código HTTP correto.
 * Não tem nenhuma lógica aqui — só delega pro serviço.
 */
@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuários", description = "Importar e gerenciar perfis do GitHub")
public class ControladorUsuario {

    @Autowired
    private ServicoGitHub servicoGitHub;

    /*
     * POST /api/usuarios/{login}
     * Importa um perfil do GitHub pelo login e salva no banco.
     * Exemplo: POST /api/usuarios/torvalds
     */
    @PostMapping("/{login}")
    @Operation(
        summary = "Importar usuário do GitHub",
        description = "Busca o perfil e os repositórios na API do GitHub e salva tudo no banco local."
    )
    public ResponseEntity<DevUser> importar(@PathVariable String login) {
        DevUser usuario = servicoGitHub.importarUsuario(login);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario); // HTTP 201
    }

    /*
     * GET /api/usuarios
     * Lista todos os usuários que já foram importados.
     */
    @GetMapping
    @Operation(summary = "Listar usuários salvos no banco")
    public ResponseEntity<List<DevUser>> listarTodos() {
        return ResponseEntity.ok(servicoGitHub.listarTodos()); // HTTP 200
    }

    /*
     * GET /api/usuarios/{id}
     * Busca um usuário pelo ID do banco (não pelo login do GitHub).
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID")
    public ResponseEntity<DevUser> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicoGitHub.buscarPorId(id));
    }

    /*
     * PUT /api/usuarios/{id}
     * Atualiza dados editáveis de um usuário já salvo.
     * Não chama a API do GitHub — edição manual.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados do usuário")
    public ResponseEntity<DevUser> atualizar(@PathVariable Long id, @RequestBody DevUser dadosNovos) {
        return ResponseEntity.ok(servicoGitHub.atualizar(id, dadosNovos));
    }

    /*
     * DELETE /api/usuarios/{id}
     * Remove o usuário e todos os dados relacionados do banco.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Remover usuário")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        servicoGitHub.deletar(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }

    /*
     * GET /api/usuarios/{login}/relatorio
     *
     * ENDPOINT AGREGADO — esse é o que a rubrica exige como "Consulta Agregada".
     * Retorna dados do banco local + dados buscados agora na API do GitHub.
     * Se a API estiver fora, usa o cache salvo e informa o status.
     */
    @GetMapping("/{login}/relatorio")
    @Operation(
        summary = "Relatório agregado",
        description = "Combina dados locais do banco com dados em tempo real da API do GitHub."
    )
    public ResponseEntity<RelatorioUsuario> relatorio(@PathVariable String login) {
        return ResponseEntity.ok(servicoGitHub.gerarRelatorio(login));
    }
}
