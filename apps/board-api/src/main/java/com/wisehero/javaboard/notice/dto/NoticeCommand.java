package com.wisehero.javaboard.notice.dto;

import com.wisehero.javaboard.notice.NoticeStatus;

import java.time.LocalDateTime;

public record NoticeCommand() {

    public record Create(
        String title,
        String content,
        LocalDateTime publishedAt,
        NoticeStatus noticeStatus,
        Long memberId
    ){
    }

    public record Update(
            Long noticeId,
            String title,
            String content,
            LocalDateTime publishedAt
    ){

    }

}
