package com.philippo.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    INVALID_PARAMETER("/invalid-parameter", "Invalid parameter"),
    UNKNOWN_PROPERTY("/unknown-property", "Unknown property"),
    MESSAGE_NOT_READABLE("/message-not-readable", "Message not readable"),
    ENTITY_NOT_FOUND("/entity-not-found", "Entity not found"),
    ENTITY_IN_USE("/entity-in-use", "Entity in use"),
    BUSINESS_ERROR("/business-error", "Business rule violation");

    private String title;
    private String uri;

    ProblemType(String path, String title){
        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }
}
