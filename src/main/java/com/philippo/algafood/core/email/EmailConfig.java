package com.philippo.algafood.core.email;

import com.philippo.algafood.domain.infrastructure.service.email.FakeEmailService;
import com.philippo.algafood.domain.infrastructure.service.email.SmptEmailService;
import com.philippo.algafood.domain.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EmailService emailService() {
        switch(emailProperties.getImpl()){
            case FAKE:
                return new FakeEmailService();
            case SMTP:
                return new SmptEmailService();
            default:
                return null;
        }
    }
}
