package com.itss.crud.core.exception;

public class ClienteNaoEncontradoException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ClienteNaoEncontradoException(String mensagem, Long cidadeId) {
        this(String.format(mensagem, cidadeId));
    }
}
