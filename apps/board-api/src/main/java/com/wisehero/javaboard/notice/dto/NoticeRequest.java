package com.wisehero.javaboard.notice.dto;

import com.wisehero.javaboard.notice.NoticeStatus;

import java.time.LocalDateTime;

public record NoticeRequest(
) {

    public record Create(
            String title,
            String content,
            LocalDateTime publishedAt,
            String status
    ) {
        public NoticeCommand.Create toCommand(Long memberId) {
            return new NoticeCommand.Create(
                    title,
                    content,
                    publishedAt,
                    NoticeStatus.fromString(status),
                    memberId
            );
        }
    }

    public record Update(
            String title,
            String content,
            LocalDateTime publishedAt
    ) {
        public NoticeCommand.Update toCommand(Long noticeId) {
            return new NoticeCommand.Update(
                    noticeId,
                    title,
                    content,
                    publishedAt
            );
        }
    }
}
