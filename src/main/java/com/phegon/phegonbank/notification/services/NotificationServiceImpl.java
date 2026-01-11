package com.phegon.phegonbank.notification.services;

import com.phegon.phegonbank.auth_users.entity.User;
import com.phegon.phegonbank.notification.dtos.NotificationDto;
import com.phegon.phegonbank.notification.entity.Notification;
import com.phegon.phegonbank.notification.repo.NotificationRepository;
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

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
@Async
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repo;
    private final JavaMailSender sender;
    private final TemplateEngine template;

    @Override
    public void sendEmail(NotificationDto dto, User user) {
        try {
            MimeMessage mimeMessage = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                    mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name()
            );

            helper.setTo(dto.getRecipient());
            helper.setSubject(dto.getSubject());

            if(dto.getTemplateName() != null) {
                //use template if provided
                Context context = new Context();
                context.setVariables(dto.getTemplateVariables());
                String htmlContent = template.process(dto.getTemplateName(), context);
                helper.setText(htmlContent, true);
            }else {
                helper.setText(dto.getBody());
            }

            //save message to database
            Notification notification = Notification.builder()
                    .recipient(dto.getRecipient())
                    .subject(dto.getSubject())
                    .messageBody(dto.getBody())
                    .notificationType(dto.getType())
                    .user(user)
                    .build();

            repo.save(notification);

            sender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error(e.getMessage());
        }
    }
}
