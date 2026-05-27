package com.mackenzie.repomonitor.dto;

import java.util.List;

public class RelatorioUsuario {

    private String login;
    private String nome;
    private Integer totalProjetosSalvos;
    private Integer totalFavoritos;
    private List<String> nomeDosProjetosSalvos;
    private Integer seguidoresAgora;
    private Integer repositoriosPublicosAgora;
    private String bioAtual;
    private String statusDaApi;

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Integer getTotalProjetosSalvos() { return totalProjetosSalvos; }
    public void setTotalProjetosSalvos(Integer t) { this.totalProjetosSalvos = t; }
    public Integer getTotalFavoritos() { return totalFavoritos; }
    public void setTotalFavoritos(Integer t) { this.totalFavoritos = t; }
    public List<String> getNomeDosProjetosSalvos() { return nomeDosProjetosSalvos; }
    public void setNomeDosProjetosSalvos(List<String> n) { this.nomeDosProjetosSalvos = n; }
    public Integer getSeguidoresAgora() { return seguidoresAgora; }
    public void setSeguidoresAgora(Integer s) { this.seguidoresAgora = s; }
    public Integer getRepositoriosPublicosAgora() { return repositoriosPublicosAgora; }
    public void setRepositoriosPublicosAgora(Integer r) { this.repositoriosPublicosAgora = r; }
    public String getBioAtual() { return bioAtual; }
    public void setBioAtual(String b) { this.bioAtual = b; }
    public String getStatusDaApi() { return statusDaApi; }
    public void setStatusDaApi(String s) { this.statusDaApi = s; }
}