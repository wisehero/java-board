package com.wisehero.javaboard.notification.interfaces.dto;

import java.time.LocalDateTime;

public record NotificationResponse() {

    public record Summary(
            Long id,
            String title,
            String url,
            boolean isRead,
            String type,
            LocalDateTime createdAt
    ) {

    }

    public record Read(
            String redirectUrl
    ) {

    }
}
