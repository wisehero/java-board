package com.wisehero.javaboard.notice;

import com.wisehero.javaboard.config.db.BaseTimeEntity;
import com.wisehero.javaboard.notice.dto.NoticeCommand;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Notice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "admin_id", nullable = false)
    private Long adminId;

    @Column(name = "is_pinned", nullable = false)
    private Boolean isPinned = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private NoticeStatus status;

    @Column(name = "published_at", nullable = false)
    private LocalDateTime publishedAt;

    @Column(name = "view_count", nullable = false)
    private Integer viewCount = 0;

    public static Notice create(NoticeCommand.Create input) {
        LocalDateTime now = LocalDateTime.now();
        Notice notice = new Notice();

        notice.title = input.title();
        notice.content = input.content();
        notice.adminId = input.memberId();
        notice.publishedAt = (input.publishedAt() != null) ? input.publishedAt() : now;
        if (input.noticeStatus() == NoticeStatus.DRAFT) {
            notice.status = NoticeStatus.DRAFT;
        }
        else if (notice.publishedAt.isAfter(now)) {
            notice.status = NoticeStatus.RESERVED;
        }
        else {
            notice.status = NoticeStatus.PUBLISHED;
        }
        return notice;
    }

    public void update(NoticeCommand.Update input) {
        this.title = input.title();
        this.content = input.content();
        this.publishedAt = input.publishedAt();
    }

    public void increaseViewCount() {
        this.viewCount++;
    }

    public void publish() {
        this.status = NoticeStatus.PUBLISHED;
    }
}
