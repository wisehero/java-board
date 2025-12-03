package com.wisehero.javaboard.notice;

import com.wisehero.javaboard.notice.infra.NoticeJpaRepository;
import com.wisehero.javaboard.support.error.CoreException;
import com.wisehero.javaboard.support.error.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeFinder {

    private final NoticeJpaRepository noticeJpaRepository;

    public Notice findById(Long id) {
        return noticeJpaRepository.findByIdNotDeleted(id)
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND, "Notice not found with id: " + id));
    }
}
