package com.philippo.algafood.domain.service;


import lombok.Builder;
import lombok.Getter;

import java.util.Set;

public interface EmailService {

    void send(Message message);

    @Getter
    @Builder
    class Message {
        private Set<String> recipients;
        private String subject;
        private String body;
    }
}
