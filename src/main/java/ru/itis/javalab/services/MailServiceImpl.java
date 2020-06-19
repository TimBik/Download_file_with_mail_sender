package ru.itis.javalab.services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    @Qualifier(value = "getConfig")
    private Configuration freemarkerConfig;

    @Override
    public void sendMessage(String subject, String mail, Map model, String ftl) {

        try {
            Template t = freemarkerConfig.getTemplate(ftl);

            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            MimeMessagePreparator messagePreparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                        StandardCharsets.UTF_8.name());
                messageHelper.setFrom("Nobody");
                messageHelper.setTo(mail);
                messageHelper.setSubject(subject);
                messageHelper.setText(html, true);
            };
            mailSender.send(messagePreparator);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }

    }
}