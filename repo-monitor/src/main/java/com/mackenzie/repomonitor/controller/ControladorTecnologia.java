package com.mackenzie.repomonitor.controller;

import com.mackenzie.repomonitor.exception.NaoEncontradoException;
import com.mackenzie.repomonitor.model.Tecnologia;
import com.mackenzie.repomonitor.repository.TecnologiaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tecnologias")
@Tag(name = "Tecnologias", description = "Gerenciar tecnologias cadastradas")
public class ControladorTecnologia {

    @Autowired
    private TecnologiaRepository tecnologiaRepository;

    @GetMapping
    @Operation(summary = "Listar todas as tecnologias")
    public List<Tecnologia> listarTodas() {
        return tecnologiaRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Cadastrar nova tecnologia")
    public ResponseEntity<Tecnologia> cadastrar(@RequestBody Tecnologia tecnologia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tecnologiaRepository.save(tecnologia));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tecnologia")
    public ResponseEntity<Tecnologia> atualizar(@PathVariable Long id, @RequestBody Tecnologia dados) {
        Tecnologia existente = tecnologiaRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Tecnologia " + id + " não encontrada."));
        existente.setNome(dados.getNome());
        existente.setDescricao(dados.getDescricao());
        return ResponseEntity.ok(tecnologiaRepository.save(existente));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover tecnologia")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Tecnologia existente = tecnologiaRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Tecnologia " + id + " não encontrada."));
        tecnologiaRepository.delete(existente);
        return ResponseEntity.noContent().build();
    }
}
