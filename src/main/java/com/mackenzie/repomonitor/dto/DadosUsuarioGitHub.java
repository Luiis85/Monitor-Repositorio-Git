package com.mackenzie.repomonitor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DadosUsuarioGitHub {

    @JsonProperty("login")
    private String login;

    @JsonProperty("name")
    private String nome;

    @JsonProperty("avatar_url")
    private String urlFoto;

    @JsonProperty("bio")
    private String bio;

    @JsonProperty("company")
    private String empresa;

    @JsonProperty("location")
    private String localizacao;

    @JsonProperty("followers")
    private Integer seguidores;

    @JsonProperty("following")
    private Integer seguindo;

    @JsonProperty("public_repos")
    private Integer totalRepositorios;

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
}