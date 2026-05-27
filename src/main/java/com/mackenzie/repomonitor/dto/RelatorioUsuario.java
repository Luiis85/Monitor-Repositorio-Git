package com.mackenzie.repomonitor.dto;

import lombok.Data;
import java.util.List;

/*
 * Objeto de resposta do endpoint de relatório agregado.
 *
 * Esse é o endpoint que a rubrica chama de "Endpoint de Consulta Agregada":
 * ele combina informações do nosso banco local com dados buscados
 * em tempo real na API do GitHub no momento da requisição.
 *
 * Se a API estiver fora do ar, o sistema usa os dados salvos
 * no banco como fallback e informa o status.
 */
@Data
public class RelatorioUsuario {

    // --- Dados vindos do nosso banco local ---
    private String login;
    private String nome;
    private Integer totalProjetosSalvos;
    private Integer totalFavoritos;
    private List<String> nomeDosProjetosSalvos;

    // --- Dados buscados em tempo real na API do GitHub ---
    private Integer seguidoresAgora;
    private Integer repositoriosPublicosAgora;
    private String bioAtual;

    // Indica se a API respondeu ou se estamos usando cache
    // Valores possíveis: "ONLINE" ou "API_FORA - exibindo dados salvos"
    private String statusDaApi;
}
