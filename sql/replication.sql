-- 복제용 유저 생성
CREATE USER 'repl_user'@'%' IDENTIFIED BY 'secret_repl';
GRANT REPLICATION SLAVE ON *.* TO 'repl_user'@'%';
FLUSH PRIVILEGES;

ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'verysecret';
ALTER USER 'myuser'@'%' IDENTIFIED WITH mysql_native_password BY 'secret';
ALTER USER 'repl_user'@'%' IDENTIFIED WITH mysql_native_password BY 'secret_repl';
FLUSH PRIVILEGES;

-- 마스터 상태 확인 (File과 Position 값을 메모하세요!)
SHOW MASTER STATUS;
-- 예: File='binlog.000002', Position=1665

-- 위에서 적어둔 File과 Position 값을 여기에 입력
CHANGE MASTER TO
    MASTER_HOST='mysql-master',         -- 도커 서비스 이름
    MASTER_USER='repl_user',
    MASTER_PASSWORD='secret_repl',
    MASTER_LOG_FILE='mysql-bin.000004', -- (아까 확인한 값)
    MASTER_LOG_POS=157;                 -- (아까 확인한 값)

-- 복제 시작
START SLAVE;

-- 확인 (Slave_IO_Running, Slave_SQL_Running 둘 다 'Yes'여야 함)
SHOW SLAVE STATUS;