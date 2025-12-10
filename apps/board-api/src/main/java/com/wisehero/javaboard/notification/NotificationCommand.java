package com.wisehero.javaboard.notification;

public record NotificationCommand() {

    public record Send(
            Long receiverId,
            Long senderId,
            NotificationType notificationType,
            String title,
            String content,
            String url,
            Long targetId
    ){

    }
}
