package com.wisehero.javaboard.notification;

import com.wisehero.javaboard.notification.infra.db.NotificationJpaRepository;
import com.wisehero.javaboard.notification.interfaces.dto.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationFinder {

    private final NotificationJpaRepository notificationJpaRepository;

    public int countUnreadNotifications(Long receiverId) {
        return notificationJpaRepository.countNotification(receiverId, false);
    }

    public List<NotificationResponse.Summary> getRecentUnreadNotifications(Long receiverId){
        List<Notification> notifications = notificationJpaRepository
                .findRecentUnreadNotifications(receiverId, 5);

        return notifications.stream()
                .map(this::toSummary)
                .toList();
    }

    private NotificationResponse.Summary toSummary(Notification notification) {
        return new NotificationResponse.Summary(
                notification.getId(),
                notification.getContent(),  // 이미 포맷팅된 메시지
                notification.getUrl(),
                notification.isRead(),
                notification.getNotificationType().getDescription(),
                notification.getCreatedAt()
        );
    }
}
