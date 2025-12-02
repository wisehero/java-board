-- 기존 테이블 삭제 (초기화용)
DROP TABLE IF EXISTS likes;

-- likes 테이블 생성
CREATE TABLE likes
(
    id          BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,

    member_id   BIGINT      NOT NULL,     -- 누가
    target_id   BIGINT      NOT NULL,     -- 몇 번 글(댓글)에
    target_type VARCHAR(20) NOT NULL,     -- 어떤 종류(POST/COMMENT)에

    -- BaseTimeEntity 컬럼들
    created_at  DATETIME(6),
    updated_at  DATETIME(6),
    deleted_at  DATETIME(6) DEFAULT NULL, -- Like는 주로 Hard Delete를 쓰지만 상속받았으니 일단 둡니다.

    -- [핵심] 중복 방지 유니크 인덱스
    CONSTRAINT uk_likes_member_target UNIQUE (member_id, target_type, target_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;