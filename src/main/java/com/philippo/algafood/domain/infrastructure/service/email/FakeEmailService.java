package com.philippo.algafood.domain.infrastructure.service.email;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEmailService extends SmptEmailService {

    @Override
    public void send(Message message) {
        String body = processTemplate(message);

        log.info("[FAKE EMAIL] to: {}\n{}", message.getRecipients(), body);

    }
}
