package com.wisehero.javaboard.notification;

import com.wisehero.javaboard.config.db.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long receiverId;

    @Column(nullable = true)
    private Long senderId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType notificationType;

    @Column(nullable = false)
    private String content;

    // 클릭시 이동할 URL;
    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private boolean isRead;

    @Column(name = "target_id", nullable = false)
    private Long targetId;

    private Notification(NotificationCommand.Send command) {
        this.receiverId = command.receiverId();
        this.senderId = command.senderId();
        this.notificationType = command.notificationType();
        this.content = command.content();
        this.url = command.url();
        this.targetId = command.targetId();
        this.isRead = false;
    }

    public static Notification create(NotificationCommand.Send command) {
        return new Notification(command);
    }

    public void read() {
        this.isRead = true;
    }
}
