package com.philippo.algafood.domain.infrastructure.service.email;

import com.philippo.algafood.core.email.EmailProperties;
import com.philippo.algafood.domain.service.EmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class SmptEmailService implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private Configuration freeMarkerConfig;

    @Override
    public void send(Message message) {
        try{
            MimeMessage mimeMessage = createMimeMessage(message);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Unable to send email", e);
        }
    }

    protected MimeMessage createMimeMessage(Message message) throws MessagingException {
        String body = processTemplate(message);

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setFrom(emailProperties.getSender());
        helper.setTo(message.getRecipients().toArray(new String[0]));
        helper.setSubject(message.getSubject());
        helper.setText(body, true);

        return mimeMessage;
    }

    protected String processTemplate(Message message){
        try {
            Template template = freeMarkerConfig.getTemplate(message.getBody());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, message.getVariables());
        } catch (Exception e) {
            throw new EmailException("Unable to create the email template", e);
        }
    }
}
