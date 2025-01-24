package com.philippo.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    INVALID_DATA("/invalid-data", "Invalid data"),
    SYSTEM_ERROR("/error-system", "Error system"),
    INVALID_PARAMETER("/invalid-parameter", "Invalid parameter"),
    UNKNOWN_PROPERTY("/unknown-property", "Unknown property"),
    MESSAGE_NOT_READABLE("/message-not-readable", "Message not readable"),
    RESOURCE_NOT_FOUND("/resource-not-found", "Resource not found"),
    ENTITY_IN_USE("/entity-in-use", "Entity in use"),
    BUSINESS_ERROR("/business-error", "Business rule violation"),
    ACCESS_DENIED("/access-denied", "Access denied");

    private final String title;
    private final String uri;

    ProblemType(String path, String title){
        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }
}
