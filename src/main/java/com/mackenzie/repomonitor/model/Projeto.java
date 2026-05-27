package com.mackenzie.repomonitor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

/*
 * Representa um repositório do GitHub salvo no banco.
 * Quando o sistema importa um usuário, os repositórios públicos
 * dele são buscados na API e salvos como registros dessa classe.
 */
@Data
@Entity
@Table(name = "projeto")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;
    private String urlGithub;
    private Integer estrelas;
    private Integer forks;
    private String linguagemPrincipal;
    private Boolean privado;

    /*
     * Relacionamento N:1 com DevUser.
     * @JsonIgnore aqui evita que ao serializar Projeto,
     * o sistema tente serializar DevUser inteiro (com a lista de projetos dele),
     * causando loop infinito.
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dono_id", nullable = false)
    private DevUser dono;

    /*
     * Relacionamento N:N com Tecnologia.
     * Um projeto pode usar várias tecnologias,
     * e a mesma tecnologia pode aparecer em vários projetos.
     * @JoinTable cria a tabela intermediária "projeto_tecnologia" no banco.
     */
    @ManyToMany
    @JoinTable(
        name = "projeto_tecnologia",
        joinColumns = @JoinColumn(name = "projeto_id"),
        inverseJoinColumns = @JoinColumn(name = "tecnologia_id")
    )
    private List<Tecnologia> tecnologias;

    @JsonIgnore
    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private List<Favorito> favoritos;
}
