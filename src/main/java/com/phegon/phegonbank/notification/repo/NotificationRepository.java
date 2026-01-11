package com.phegon.phegonbank.notification.repo;

import com.phegon.phegonbank.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
