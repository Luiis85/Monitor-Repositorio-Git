package com.mackenzie.repomonitor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

/*
 * Representa uma linguagem ou tecnologia usada nos projetos.
 * Exemplos: Java, Python, JavaScript, TypeScript.
 * Essa tabela é preenchida automaticamente conforme os repositórios
 * são importados do GitHub.
 */
@Data
@Entity
@Table(name = "tecnologia")
public class Tecnologia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Não pode ter duas tecnologias com o mesmo nome
    @Column(unique = true, nullable = false)
    private String nome;

    private String descricao;

    // @JsonIgnore evita serializar a lista de projetos ao retornar uma tecnologia
    @JsonIgnore
    @ManyToMany(mappedBy = "tecnologias")
    private List<Projeto> projetos;
}
