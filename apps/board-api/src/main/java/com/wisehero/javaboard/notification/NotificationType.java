package com.wisehero.javaboard.notification;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.EnumSet;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum NotificationType {

    COMMENT("댓글", EnumSet.of(NotificationChannel.WEB, NotificationChannel.PUSH)),
    REPLY("대댓글", EnumSet.of(NotificationChannel.WEB, NotificationChannel.PUSH)),
    LIKE("좋아요", EnumSet.of(NotificationChannel.WEB, NotificationChannel.PUSH)),
    QNA("Q&A", EnumSet.of(NotificationChannel.WEB, NotificationChannel.PUSH, NotificationChannel.EMAIL)),
    MESSAGE("쪽지", EnumSet.of(NotificationChannel.WEB, NotificationChannel.PUSH)),
    NOTICE("공지사항", EnumSet.of(NotificationChannel.WEB, NotificationChannel.PUSH, NotificationChannel.EMAIL, NotificationChannel.SMS));

    private final String description;
    private final Set<NotificationChannel> supportedChannels;

    public boolean supports(NotificationChannel channel) {
        return supportedChannels.contains(channel);
    }
}
