package com.wisehero.javaboard.notice;

import com.wisehero.javaboard.notice.dto.NoticeCommand;
import com.wisehero.javaboard.notice.dto.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeFinder noticeFinder;
    private final NoticeProcessor noticeProcessor;

    public NoticeResponse.Detail getNotice(Long noticeId) {
        Notice notice = noticeFinder.findById(noticeId);

        noticeProcessor.increaseViewCount(noticeId);

        return NoticeResponse.Detail.from(notice);
    }

    public NoticeResponse.Detail writeNotice(NoticeCommand.Create command) {
        return noticeProcessor.createNotice(command);
    }

    public void updateNotice(NoticeCommand.Update command){
        noticeProcessor.updateNotice(command);
    }

    public void deleteNotice(Long noticeId){
        noticeProcessor.deleteNotice(noticeId);
    }
}
