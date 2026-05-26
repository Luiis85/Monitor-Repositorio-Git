package com.mackenzie.repomonitor.exception;

/*
 * Exceção lançada quando um recurso não existe no banco.
 * Quando essa exceção sobe até o TratadorDeErros,
 * ele devolve automaticamente um HTTP 404 para quem chamou a API.
 */
public class NaoEncontradoException extends RuntimeException {

    public NaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
