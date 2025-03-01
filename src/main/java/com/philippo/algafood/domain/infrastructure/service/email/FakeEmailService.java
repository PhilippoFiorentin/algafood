package com.philippo.algafood.domain.infrastructure.service.email;

import com.philippo.algafood.domain.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class FakeEmailService implements EmailService {

    @Autowired
    private EmailTemplateProcessor emailTemplateProcessor;

    @Override
    public void send(Message message) {
        String body = emailTemplateProcessor.processTemplate(message);

        log.info("[FAKE EMAIL] to: {}\n{}", message.getRecipients(), body);

    }
}
