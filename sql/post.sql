DROP TABLE IF EXISTS post;

CREATE TABLE post
(
    id            BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,

    -- 핵심 데이터
    title         VARCHAR(255) NOT NULL,
    content       TEXT         NOT NULL,
    member_id     BIGINT       NOT NULL,

    -- Enum (길이 최적화)
    post_status   VARCHAR(50)  NOT NULL,
    post_category VARCHAR(50)  NOT NULL,

    -- 카운트 (Null 방지 및 기본값 0)
    view_count    INT          NOT NULL DEFAULT 0,
    like_count    INT          NOT NULL DEFAULT 0,
    comment_count INT          NOT NULL DEFAULT 0,

    -- BaseTimeEntity (상속 필드)
    created_at    DATETIME(6) NOT NULL,    -- nullable = false 반영
    updated_at    DATETIME(6) NOT NULL,    -- nullable = false 반영
    deleted_at    DATETIME(6) DEFAULT NULL -- Soft Delete용 (기본값 NULL)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;