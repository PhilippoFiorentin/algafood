package com.philippo.algafood.domain.infrastructure.service.email;

import com.philippo.algafood.domain.service.EmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Component
public class EmailTemplateProcessor {

    @Autowired
    private Configuration freeMarkerConfig;

    protected String processTemplate(EmailService.Message message){
        try {
            Template template = freeMarkerConfig.getTemplate(message.getBody());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, message.getVariables());
        } catch (Exception e) {
            throw new EmailException("Unable to create the email template", e);
        }
    }
}
