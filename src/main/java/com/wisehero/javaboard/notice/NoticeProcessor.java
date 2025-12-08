package com.wisehero.javaboard.notice;

import com.wisehero.javaboard.notice.dto.NoticeCommand;
import com.wisehero.javaboard.notice.dto.NoticeResponse;
import com.wisehero.javaboard.notice.infra.NoticeJpaRepository;
import com.wisehero.javaboard.support.error.CoreException;
import com.wisehero.javaboard.support.error.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NoticeProcessor {

    private final NoticeJpaRepository noticeJpaRepository;

    @Transactional
    public NoticeResponse.Detail createNotice(NoticeCommand.Create command) {
        Notice notice = Notice.create(command);

        Notice savedNotice = noticeJpaRepository.save(notice);

        return NoticeResponse.Detail.from(savedNotice);
    }

    @Transactional
    public void updateNotice(NoticeCommand.Update command) {
        Long noticeId = command.noticeId();
        Notice notice = noticeJpaRepository.findById(noticeId).orElseThrow(
                () -> new CoreException(ErrorType.NOT_FOUND, "공지사항을 찾을 수 없습니다. 공지사항 ID: " + noticeId));

        notice.update(command);

        noticeJpaRepository.save(notice);
    }

    @Transactional
    public void deleteNotice(Long noticeId) {
        Notice notice = noticeJpaRepository.findById(noticeId).orElseThrow(
                () -> new CoreException(ErrorType.NOT_FOUND, "공지사항을 찾을 수 없습니다. 공지사항 ID: " + noticeId));

        notice.delete();

        noticeJpaRepository.save(notice);
    }

    @Transactional
    public void increaseViewCount(Long noticeId) {
        Notice notice = noticeJpaRepository.findById(noticeId).orElseThrow(
                () -> new CoreException(ErrorType.NOT_FOUND, "공지사항을 찾을 수 없습니다. 공지사항 ID: " + noticeId));
        notice.increaseViewCount();

        noticeJpaRepository.save(notice);
    }

    @Transactional
    public int publishedReservedNotices() {
        LocalDateTime now = LocalDateTime.now();

        List<Notice> reservedNotices = noticeJpaRepository
                .findAllByStatusAndPublishedAtLessThanEqual(NoticeStatus.RESERVED, now);
        for (Notice notice : reservedNotices) {
            notice.publish();
        }

        return reservedNotices.size();
    }
}
