package com.wisehero.javaboard.notification;

import com.wisehero.javaboard.notification.infra.db.NotificationJpaRepository;
import com.wisehero.javaboard.support.error.CoreException;
import com.wisehero.javaboard.support.error.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationProcessor {

    private final NotificationJpaRepository notificationJpaRepository;

    @Transactional
    public Notification append(NotificationCommand.Send command) {

        Notification notification = Notification.create(command);

        return notificationJpaRepository.save(notification);
    }

    @Transactional
    public String read(Long receiverId, Long notificationId) {
        Notification notification = notificationJpaRepository.findByIdAndDeletedAtFalse(notificationId)
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND, "Notification not found with id: " + notificationId));

        if (notification.getReceiverId().equals(receiverId)){
            throw new CoreException(ErrorType.BAD_REQUEST, "You do not have permission to read this notification.");
        }

        notification.read();

        return notification.getUrl();
    }

    @Transactional
    public void delete(Long notificationId) {
        Notification notification = notificationJpaRepository.findByIdAndDeletedAtFalse(notificationId)
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND,
                        "Notification not found with id: " + notificationId));
        notification.delete();
    }
}
