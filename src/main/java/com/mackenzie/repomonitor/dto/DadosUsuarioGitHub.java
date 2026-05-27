package com.mackenzie.repomonitor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/*
 * Objeto usado para receber a resposta da API do GitHub
 * quando consultamos o perfil de um usuário.
 *
 * O GitHub devolve um JSON com nomes em inglês (ex: "avatar_url", "public_repos").
 * O @JsonProperty faz o mapeamento: pega o campo do JSON e coloca
 * no atributo certo dessa classe.
 *
 * Usamos um DTO separado (em vez da entidade DevUser diretamente) para
 * isolar o formato da API externa do nosso modelo interno.
 */
@Data
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
}
