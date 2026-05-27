package com.mackenzie.repomonitor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tecnologia")
public class Tecnologia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    private String descricao;

    @JsonIgnore
    @ManyToMany(mappedBy = "tecnologias")
    private List<Projeto> projetos;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public List<Projeto> getProjetos() { return projetos; }
    public void setProjetos(List<Projeto> projetos) { this.projetos = projetos; }
}