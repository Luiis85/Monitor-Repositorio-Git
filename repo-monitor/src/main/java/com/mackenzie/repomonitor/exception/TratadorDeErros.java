package com.mackenzie.repomonitor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;

/*
 * Captura exceções lançadas em qualquer Controller e devolve
 * uma resposta JSON organizada com o código HTTP correto.
 *
 * Sem essa classe, o Spring devolveria uma página de erro genérica.
 * Com ela, o cliente recebe sempre um JSON no formato:
 * { "erro": "mensagem explicando o que aconteceu" }
 */
@RestControllerAdvice
public class TratadorDeErros {

    // Recurso não encontrado → HTTP 404
    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> tratarNaoEncontrado(NaoEncontradoException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(Map.of("erro", ex.getMessage()));
    }

    // Falha de conexão com a API do GitHub → HTTP 502
    @ExceptionHandler(java.net.ConnectException.class)
    public ResponseEntity<Map<String, String>> tratarFalhaDeConexao(Exception ex) {
        return ResponseEntity
            .status(HttpStatus.BAD_GATEWAY)
            .body(Map.of("erro", "Não foi possível conectar à API do GitHub. Verifique a conexão."));
    }

    // Qualquer outro erro não esperado → HTTP 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> tratarErroGeral(Exception ex) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("erro", "Erro interno no servidor: " + ex.getMessage()));
    }
}
