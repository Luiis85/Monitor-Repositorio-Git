package com.mackenzie.repomonitor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/*
 * Representa um projeto marcado como favorito.
 * O usuário pode adicionar uma observação pessoal ao favoritar,
 * e o sistema registra automaticamente a data e hora.
 */
@Data
@Entity
@Table(name = "favorito")
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime favoritadoEm;

    // Anotação opcional (ex: "ver depois", "referência boa")
    private String observacao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;
}
