package com.wisehero.javaboard.notice;

import com.wisehero.javaboard.support.error.CoreException;
import com.wisehero.javaboard.support.error.ErrorType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NoticeStatus {
    DRAFT("임시 저장"),
    RESERVED("예약"),
    PUBLISHED("게시");


    private final String description;

    public static NoticeStatus fromString(String status) {
        if (status == null || status.isEmpty()) {
            throw new CoreException(ErrorType.BAD_REQUEST, "Notice status cannot be null or empty");
        }
        for (NoticeStatus noticeStatus : NoticeStatus.values()) {
            if (noticeStatus.name().equalsIgnoreCase(status)) {
                return noticeStatus;
            }
        }
        throw new CoreException(ErrorType.BAD_REQUEST, "Invalid notice status: " + status);
    }
}
