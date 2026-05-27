package com.mackenzie.repomonitor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "dev_user")
public class DevUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @JsonIgnore
    @OneToMany(mappedBy = "dono", cascade = CascadeType.ALL)
    private List<Projeto> projetos;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<RegistroConsulta> consultas;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getUrlFoto() { return urlFoto; }
    public void setUrlFoto(String urlFoto) { this.urlFoto = urlFoto; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }
    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
    public Integer getSeguidores() { return seguidores; }
    public void setSeguidores(Integer seguidores) { this.seguidores = seguidores; }
    public Integer getSeguindo() { return seguindo; }
    public void setSeguindo(Integer seguindo) { this.seguindo = seguindo; }
    public Integer getTotalRepositorios() { return totalRepositorios; }
    public void setTotalRepositorios(Integer totalRepositorios) { this.totalRepositorios = totalRepositorios; }
    public List<Projeto> getProjetos() { return projetos; }
    public void setProjetos(List<Projeto> projetos) { this.projetos = projetos; }
    public List<RegistroConsulta> getConsultas() { return consultas; }
    public void setConsultas(List<RegistroConsulta> consultas) { this.consultas = consultas; }
}