package com.mackenzie.repomonitor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dono_id", nullable = false)
    private DevUser dono;

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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getUrlGithub() { return urlGithub; }
    public void setUrlGithub(String urlGithub) { this.urlGithub = urlGithub; }
    public Integer getEstrelas() { return estrelas; }
    public void setEstrelas(Integer estrelas) { this.estrelas = estrelas; }
    public Integer getForks() { return forks; }
    public void setForks(Integer forks) { this.forks = forks; }
    public String getLinguagemPrincipal() { return linguagemPrincipal; }
    public void setLinguagemPrincipal(String linguagemPrincipal) { this.linguagemPrincipal = linguagemPrincipal; }
    public Boolean getPrivado() { return privado; }
    public void setPrivado(Boolean privado) { this.privado = privado; }
    public DevUser getDono() { return dono; }
    public void setDono(DevUser dono) { this.dono = dono; }
    public List<Tecnologia> getTecnologias() { return tecnologias; }
    public void setTecnologias(List<Tecnologia> tecnologias) { this.tecnologias = tecnologias; }
    public List<Favorito> getFavoritos() { return favoritos; }
    public void setFavoritos(List<Favorito> favoritos) { this.favoritos = favoritos; }
}