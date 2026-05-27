package com.mackenzie.repomonitor.controller;

import com.mackenzie.repomonitor.exception.NaoEncontradoException;
import com.mackenzie.repomonitor.model.Projeto;
import com.mackenzie.repomonitor.repository.ProjetoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projetos")
@Tag(name = "Projetos", description = "Consultar e gerenciar repositórios salvos")
public class ControladorProjeto {

    @Autowired
    private ProjetoRepository projetoRepository;

    // GET /api/projetos — lista todos os projetos salvos
   @GetMapping
@Operation(summary = "Listar todos os projetos salvos")
public List<Projeto> listarTodos(@RequestParam(required = false) String login) {
    if (login != null) {
        return projetoRepository.findByDonoLogin(login);
    }
    return projetoRepository.findAll();
}

    // GET /api/projetos/{id} — busca um projeto pelo ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar projeto por ID")
    public ResponseEntity<Projeto> buscarPorId(@PathVariable Long id) {
        Projeto projeto = projetoRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Projeto com id " + id + " não encontrado."));
        return ResponseEntity.ok(projeto);
    }

    // GET /api/projetos/por-linguagem/Java — filtra projetos por linguagem
    @GetMapping("/por-linguagem/{linguagem}")
    @Operation(summary = "Filtrar projetos por linguagem")
    public List<Projeto> porLinguagem(@PathVariable String linguagem) {
        return projetoRepository.findByLinguagemPrincipal(linguagem);
    }

    // DELETE /api/projetos/{id} — remove um projeto do banco
    @DeleteMapping("/{id}")
    @Operation(summary = "Remover projeto")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Projeto projeto = projetoRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Projeto com id " + id + " não encontrado."));
        projetoRepository.delete(projeto);
        return ResponseEntity.noContent().build();
    }
}
