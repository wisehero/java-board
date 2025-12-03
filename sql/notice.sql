DROP TABLE IF EXISTS notice;

CREATE TABLE notice
(
    id           BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,

    -- 핵심 데이터
    title        VARCHAR(255) NOT NULL,
    content      TEXT         NOT NULL,
    admin_id     BIGINT       NOT NULL,             -- 관리자 ID (간접 참조)
    scheduled_at DATETIME(6)  NOT NULL,             -- 예약 게시 시간

    -- [추가] 조회수 (Not Null, 기본값 0)
    view_count   INT          NOT NULL DEFAULT 0,

    -- BaseTimeEntity 상속 필드
    created_at   DATETIME(6)  NOT NULL,
    updated_at   DATETIME(6)  NOT NULL,
    deleted_at   DATETIME(6)           DEFAULT NULL -- Soft Delete용

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

ALTER TABLE notice
ADD COLUMN is_pinned TINYINT(1) NOT NULL DEFAULT 0 AFTER scheduled_at;

ALTER TABLE notice
    ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'PUBLISHED' AFTER is_pinned;

ALTER TABLE notice
RENAME COLUMN scheduled_at TO published_at;