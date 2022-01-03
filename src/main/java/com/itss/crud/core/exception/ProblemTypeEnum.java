package com.itss.crud.core.exception;

import lombok.Getter;

@Getter
public enum ProblemTypeEnum {

    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    INVALID_DATA("/Dados-invalodos", "Dados invalidos");

    private String title;
    private String uri;

    ProblemTypeEnum(String path, String title) {
        this.uri = "https://crud" + path;
        this.title = title;
    }

}