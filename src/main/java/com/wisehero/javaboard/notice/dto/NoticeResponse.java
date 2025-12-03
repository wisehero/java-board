package com.wisehero.javaboard.notice.dto;

import com.wisehero.javaboard.notice.Notice;

import java.time.LocalDateTime;


public record NoticeResponse(

) {
    private static final int NEW_NOTICE_DAYS = 3;

    public record Detail(
            String title,
            String content,
            int viewCount,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        public static Detail from(Notice notice) {
            return new Detail(
                    notice.getTitle(),
                    notice.getContent(),
                    notice.getViewCount(),
                    notice.getCreatedAt(),
                    notice.getUpdatedAt()
            );
        }
    }

    public record Summary(
            Long id,
            String title,
            boolean isPinned,
            boolean isNew,
            int viewCount,
            LocalDateTime createdAt
    ) {
        public static Summary from(Notice notice) {
            return new Summary(
                    notice.getId(),
                    notice.getTitle(),
                    notice.getIsPinned(),
                    notice.getCreatedAt().isAfter(LocalDateTime.now().minusDays(NEW_NOTICE_DAYS)),
                    notice.getViewCount(),
                    notice.getCreatedAt()
            );
        }
    }

}
