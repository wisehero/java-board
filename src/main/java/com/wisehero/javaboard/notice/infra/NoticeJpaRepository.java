package com.wisehero.javaboard.notice.infra;

import com.wisehero.javaboard.notice.Notice;
import com.wisehero.javaboard.notice.NoticeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NoticeJpaRepository extends JpaRepository<Notice, Long> {

    @Query("SELECT n FROM Notice n WHERE n.id = :id AND n.deletedAt is null")
    Optional<Notice> findByIdNotDeleted(Long id);

    List<Notice> findAllByStatusAndPublishedAtLessThanEqual(NoticeStatus status, LocalDateTime publishedAt);
}
