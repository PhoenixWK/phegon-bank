package com.phegon.phegonbank;

import com.phegon.phegonbank.auth_users.entity.User;
import com.phegon.phegonbank.enums.NotificationType;
import com.phegon.phegonbank.notification.dtos.NotificationDto;
import com.phegon.phegonbank.notification.entity.Notification;
import com.phegon.phegonbank.notification.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@RequiredArgsConstructor
public class PhegonbankApplication {

	private final NotificationService notificationService;

	public static void main(String[] args) {
		SpringApplication.run(PhegonbankApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			NotificationDto dto = NotificationDto.builder()
					.recipient("nguyenxuanphu204@gmail.com")
					.subject("Hello Testing email")
					.body("Hey, this is test email")
					.type(NotificationType.EMAIL)
					.build();
			notificationService.sendEmail(dto, new User());
		};
	}
}
