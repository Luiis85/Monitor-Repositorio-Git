package com.mackenzie.repomonitor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DadosRepositorioGitHub {

    @JsonProperty("name")
    private String nome;

    @JsonProperty("description")
    private String descricao;

    @JsonProperty("html_url")
    private String urlGithub;

    @JsonProperty("stargazers_count")
    private Integer estrelas;

    @JsonProperty("forks_count")
    private Integer forks;

    @JsonProperty("language")
    private String linguagemPrincipal;

    @JsonProperty("private")
    private Boolean privado;

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
}