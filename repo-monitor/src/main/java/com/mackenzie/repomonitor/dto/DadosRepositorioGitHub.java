package com.mackenzie.repomonitor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/*
 * Objeto usado para receber cada repositório na resposta da API do GitHub.
 * A API devolve uma lista desses objetos quando pedimos
 * os repos de um usuário.
 */
@Data
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
}
