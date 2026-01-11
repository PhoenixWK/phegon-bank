package com.phegon.phegonbank.notification.services;

import com.phegon.phegonbank.auth_users.entity.User;
import com.phegon.phegonbank.notification.dtos.NotificationDto;

public interface NotificationService {
    void sendEmail(NotificationDto dto, User user);
}
