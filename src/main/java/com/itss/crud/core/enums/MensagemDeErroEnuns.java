package com.itss.crud.core.enums;

import lombok.Getter;

@Getter
public enum MensagemDeErroEnuns {

    MSG_ERRO_EMAIL_CADASTRADO("E-mail já cadastro ."),
    MSG_ERRO_CODIGO_INVALIDO("Código informado está invalido %d.");
    private String mensagem;

    MensagemDeErroEnuns(String mensagem) {
        this.mensagem = mensagem;
    }
}
