package com.wisehero.javaboard.notice.scheduler;


import com.wisehero.javaboard.notice.NoticeProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NoticeScheduler {

    private final NoticeProcessor noticeProcessor;

    @Scheduled(cron = "0 * * * * *")
    public void reservedNoticePublish() {
        int count = noticeProcessor.publishedReservedNotices();

        if (count > 0) {
            log.info("[reservedNoticePublish] : 예약된 공지사항 {}건이 게시되었습니다.", count);
        }
    }
}
