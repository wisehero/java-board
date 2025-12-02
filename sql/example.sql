-- ExampleEntity DDL

DROP TABLE IF EXISTS example;

CREATE TABLE example (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    description VARCHAR(255),
    
    -- BaseTimeEntity 컬럼들
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    deleted_at DATETIME(6) DEFAULT NULL,
    
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 인덱스 (필요시 추가)
-- CREATE INDEX idx_example_name ON example(name);
-- CREATE INDEX idx_example_deleted_at ON example(deleted_at);

-- 샘플 데이터
INSERT INTO example (name, description, created_at, updated_at) VALUES
('Example 1', 'First example description', NOW(), NOW()),
('Example 2', 'Second example description', NOW(), NOW()),
('Example 3', 'Third example description', NOW(), NOW());
