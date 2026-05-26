package com.mackenzie.repomonitor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/*
 * Registra cada chamada feita à API do GitHub.
 * Funciona como um histórico de auditoria: quando foi chamado,
 * qual endereço foi consultado, e se deu certo ou errado.
 */
@Data
@Entity
@Table(name = "registro_consulta")
public class RegistroConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime realizadaEm;

    // Resultado da consulta: "SUCESSO", "USUARIO_NAO_ENCONTRADO", "FALHA_REDE"
    private String resultado;

    // O endereço da API que foi chamado (ex: "/users/torvalds")
    private String enderecoConsultado;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private DevUser usuario;
}
