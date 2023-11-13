package com.example.moviesbackend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {
    @Value("${spring.mail.verify.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender emailSender;

    private final TemplateEngine templateEngine;

    @Async
    public void sendMessage(String name, String to, String token) {
        try {
            String infoText = "Please click below to verify your new account:";
            String url = host + "/api/v1/auth?token=" + token;

            Context context = new Context();
            context.setVariables(Map.of("name", name, "url", url, "infoText", infoText));

            String text = templateEngine.process("accountValidationEmail", context);

            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setSubject("Email verification");
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(text, true);

            emailSender.send(message);

        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
