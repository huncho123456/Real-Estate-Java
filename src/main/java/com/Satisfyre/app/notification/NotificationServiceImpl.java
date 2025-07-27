package com.Satisfyre.app.notification;

import com.Satisfyre.app.repo.NotificationRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {


    private final JavaMailSender javaMailSender;
    private final NotificationRepository notificationRepository;
    private final TemplateEngine templateEngine;

    @Override
    @Async
    public void sendVerificationEmail(String to, String name, String link) {

        try {
            Context context = new Context();
            context.setVariable("name", name);
            context.setVariable("verificationLink", link);

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper sender = new MimeMessageHelper(message, true, "UTF-8");

            String htmlContent = templateEngine.process("email-template/verification-template", context);

            sender.setTo(to);
            sender.setFrom("micheal.okafor.79677@gmail.com");
            sender.setSubject("Verify Your Satisfyre Account");
            sender.setText(htmlContent, true); // true = HTML

            javaMailSender.send(message);
            log.info("Verification email sent to: {}", to);

        } catch (MessagingException e) {
            log.error("Failed to send verification email to {}: {}", to, e.getMessage());
        }

    }

    @Override
    public void sendSms() {

    }

    @Override
    public void sendWhatsapp() {

    }
}
