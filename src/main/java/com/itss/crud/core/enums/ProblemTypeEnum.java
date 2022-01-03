package com.itss.crud.core.enums;

import lombok.Getter;

@Getter
public enum ProblemTypeEnum {

    ENITADE_NAO_ENCONTRADA("/entidade_nao-encontrada", "Entidade não encontrada .");

    private String title;
    private String uri;

    ProblemTypeEnum(String title, String uri) {
        this.title = title;
        this.uri = "http://crud/" + uri;
    }
}
