package com.mackenzie.repomonitor.controller;

import com.mackenzie.repomonitor.exception.NaoEncontradoException;
import com.mackenzie.repomonitor.model.Favorito;
import com.mackenzie.repomonitor.model.Projeto;
import com.mackenzie.repomonitor.repository.FavoritoRepository;
import com.mackenzie.repomonitor.repository.ProjetoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favoritos")
@Tag(name = "Favoritos", description = "Marcar e remover projetos favoritos")
public class ControladorFavorito {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @GetMapping
    @Operation(summary = "Listar todos os favoritos")
    public List<Favorito> listarTodos() {
        return favoritoRepository.findAll();
    }

    /*
     * POST /api/favoritos
     * Body esperado: { "projetoId": 1, "observacao": "referência boa" }
     */
    @PostMapping
    @Operation(summary = "Favoritar um projeto")
    public ResponseEntity<Favorito> favoritar(@RequestBody Map<String, Object> corpo) {
        Long idDoProjeto = Long.parseLong(corpo.get("projetoId").toString());
        String observacao = corpo.getOrDefault("observacao", "").toString();

        Projeto projeto = projetoRepository.findById(idDoProjeto)
            .orElseThrow(() -> new NaoEncontradoException("Projeto " + idDoProjeto + " não encontrado."));

        Favorito favorito = new Favorito();
        favorito.setProjeto(projeto);
        favorito.setObservacao(observacao);
        favorito.setFavoritadoEm(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CREATED).body(favoritoRepository.save(favorito));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desfavoritar projeto")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Favorito favorito = favoritoRepository.findById(id)
            .orElseThrow(() -> new NaoEncontradoException("Favorito " + id + " não encontrado."));
        favoritoRepository.delete(favorito);
        return ResponseEntity.noContent().build();
    }
}
