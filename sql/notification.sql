DROP TABLE IF EXISTS notification;

CREATE TABLE notification
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '알림 ID',

    receiver_id       BIGINT       NOT NULL COMMENT '수신자 ID',
    sender_id         BIGINT COMMENT '발신자 ID (시스템 알림인 경우 NULL)',

    notification_type VARCHAR(50)  NOT NULL COMMENT '알림 유형 (COMMENT, REPLY, QNA, NOTICE, LIKE, MESSAGE)',
    target_id         BIGINT       NOT NULL COMMENT '알림 원천 ID (댓글ID, 게시글ID 등)',

    title             VARCHAR(200) NOT NULL COMMENT '알림 제목 (모든 채널에서 사용)',
    content           TEXT COMMENT '알림 상세 내용 (EMAIL, SMS용 - 선택적)',

    url               VARCHAR(255) NOT NULL COMMENT '클릭 시 이동할 URL',
    is_read           BOOLEAN      NOT NULL DEFAULT FALSE COMMENT '읽음 여부',

    -- BaseTimeEntity 필드들
    created_at        DATETIME(6)  NOT NULL COMMENT '생성 일시',
    updated_at        DATETIME(6)  NOT NULL COMMENT '수정 일시',
    deleted_at        DATETIME(6) COMMENT '삭제 일시 (Soft Delete)'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='알림 테이블';
