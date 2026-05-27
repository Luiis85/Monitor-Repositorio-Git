package com.mackenzie.repomonitor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

/*
 * Representa um usuário do GitHub que está sendo monitorado.
 * Cada vez que alguém importa um perfil do GitHub, um registro
 * dessa classe é criado e salvo como linha na tabela "dev_user".
 */
@Data
@Entity
@Table(name = "dev_user")
public class DevUser {

    // Chave primária — o banco gera o número automaticamente (1, 2, 3...)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // O login é único: não pode ter dois usuários com o mesmo nome no banco
    @Column(unique = true, nullable = false)
    private String login;

    private String nome;
    private String urlFoto;
    private String bio;
    private String empresa;
    private String localizacao;
    private Integer seguidores;
    private Integer seguindo;
    private Integer totalRepositorios;

    /*
     * Relacionamento 1:N com Projeto.
     * @JsonIgnore evita referência circular na serialização JSON:
     * sem ele, DevUser tentaria serializar Projeto, que tentaria
     * serializar DevUser de volta, causando loop infinito.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "dono", cascade = CascadeType.ALL)
    private List<Projeto> projetos;

    // Histórico de consultas feitas à API do GitHub para esse usuário
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<RegistroConsulta> consultas;
}
