DROP TABLE IF EXISTS notification;

CREATE TABLE notification
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '알림 ID',

    receiver_id       BIGINT       NOT NULL COMMENT '수신자 ID',
    sender_id         BIGINT COMMENT '발신자 ID',

    notification_type VARCHAR(50)  NOT NULL COMMENT '알림 유형',
    target_id         BIGINT       NOT NULL COMMENT '알림 원천 ID',

    content           VARCHAR(255) NOT NULL COMMENT '알림 내용',
    url               VARCHAR(255) NOT NULL COMMENT '이동 URL',

    is_read           BOOLEAN      NOT NULL DEFAULT FALSE COMMENT '읽음 여부',

    -- BaseTimeEntity 필드들
    created_at        DATETIME(6)  NOT NULL COMMENT '생성 일시',
    updated_at        DATETIME(6)  NOT NULL COMMENT '수정 일시',
    deleted_at        DATETIME(6) COMMENT '삭제 일시 (Soft Delete)'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='알림 테이블';