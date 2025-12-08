package com.wisehero.javaboard.notice;

import com.wisehero.javaboard.notice.dto.NoticeCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Notice 도메인 테스트")
class NoticeTest {

    @Nested
    @DisplayName("공지사항 생성 테스트")
    class CreateTest {

        @Test
        @DisplayName("임시저장 상태로 생성 - DRAFT 상태일 때")
        void createWithDraftStatus() {
            // given
            LocalDateTime now = LocalDateTime.now();
            NoticeCommand.Create command = new NoticeCommand.Create(
                    "제목",
                    "내용",
                    now,
                    NoticeStatus.DRAFT,
                    1L
            );

            // when
            Notice notice = Notice.create(command);

            // then
            assertThat(notice.getTitle()).isEqualTo("제목");
            assertThat(notice.getContent()).isEqualTo("내용");
            assertThat(notice.getAdminId()).isEqualTo(1L);
            assertThat(notice.getStatus()).isEqualTo(NoticeStatus.DRAFT);
            assertThat(notice.getPublishedAt()).isEqualTo(now);
            assertThat(notice.getViewCount()).isZero();
            assertThat(notice.getIsPinned()).isFalse();
        }

        @Test
        @DisplayName("즉시 게시 - publishedAt이 현재 시간 이전일 때")
        void createWithPublishedStatus() {
            // given
            LocalDateTime pastTime = LocalDateTime.now().minusHours(1);
            NoticeCommand.Create command = new NoticeCommand.Create(
                    "제목",
                    "내용",
                    pastTime,
                    NoticeStatus.PUBLISHED,
                    1L
            );

            // when
            Notice notice = Notice.create(command);

            // then
            assertThat(notice.getStatus()).isEqualTo(NoticeStatus.PUBLISHED);
        }

        @Test
        @DisplayName("예약 게시 - publishedAt이 미래 시간일 때")
        void createWithReservedStatus() {
            // given
            LocalDateTime futureTime = LocalDateTime.now().plusHours(1);
            NoticeCommand.Create command = new NoticeCommand.Create(
                    "제목",
                    "내용",
                    futureTime,
                    NoticeStatus.PUBLISHED,
                    1L
            );

            // when
            Notice notice = Notice.create(command);

            // then
            assertThat(notice.getStatus()).isEqualTo(NoticeStatus.RESERVED);
            assertThat(notice.getPublishedAt()).isEqualTo(futureTime);
        }

        @Test
        @DisplayName("publishedAt이 null이면 현재 시간으로 설정")
        void createWithNullPublishedAt() {
            // given
            LocalDateTime before = LocalDateTime.now();
            NoticeCommand.Create command = new NoticeCommand.Create(
                    "제목",
                    "내용",
                    null,
                    NoticeStatus.PUBLISHED,
                    1L
            );

            // when
            Notice notice = Notice.create(command);
            LocalDateTime after = LocalDateTime.now();

            // then
            assertThat(notice.getPublishedAt())
                    .isAfterOrEqualTo(before)
                    .isBeforeOrEqualTo(after);
        }
    }

    @Nested
    @DisplayName("공지사항 수정 테스트")
    class UpdateTest {

        @Test
        @DisplayName("제목, 내용, 게시일자 수정")
        void update() {
            // given
            NoticeCommand.Create createCommand = new NoticeCommand.Create(
                    "원래 제목",
                    "원래 내용",
                    LocalDateTime.now(),
                    NoticeStatus.DRAFT,
                    1L
            );
            Notice notice = Notice.create(createCommand);

            LocalDateTime newPublishedAt = LocalDateTime.now().plusDays(1);
            NoticeCommand.Update updateCommand = new NoticeCommand.Update(
                    1L,
                    "수정된 제목",
                    "수정된 내용",
                    newPublishedAt
            );

            // when
            notice.update(updateCommand);

            // then
            assertThat(notice.getTitle()).isEqualTo("수정된 제목");
            assertThat(notice.getContent()).isEqualTo("수정된 내용");
            assertThat(notice.getPublishedAt()).isEqualTo(newPublishedAt);
        }
    }

    @Nested
    @DisplayName("조회수 증가 테스트")
    class ViewCountTest {

        @Test
        @DisplayName("조회수 1 증가")
        void increaseViewCount() {
            // given
            NoticeCommand.Create command = new NoticeCommand.Create(
                    "제목",
                    "내용",
                    LocalDateTime.now(),
                    NoticeStatus.PUBLISHED,
                    1L
            );
            Notice notice = Notice.create(command);

            // when
            notice.increaseViewCount();

            // then
            assertThat(notice.getViewCount()).isEqualTo(1);
        }

        @Test
        @DisplayName("조회수 여러 번 증가")
        void increaseViewCountMultipleTimes() {
            // given
            NoticeCommand.Create command = new NoticeCommand.Create(
                    "제목",
                    "내용",
                    LocalDateTime.now(),
                    NoticeStatus.PUBLISHED,
                    1L
            );
            Notice notice = Notice.create(command);

            // when
            notice.increaseViewCount();
            notice.increaseViewCount();
            notice.increaseViewCount();

            // then
            assertThat(notice.getViewCount()).isEqualTo(3);
        }
    }

    @Nested
    @DisplayName("게시 처리 테스트")
    class PublishTest {

        @Test
        @DisplayName("DRAFT -> PUBLISHED 상태 변경")
        void publishFromDraft() {
            // given
            NoticeCommand.Create command = new NoticeCommand.Create(
                    "제목",
                    "내용",
                    LocalDateTime.now(),
                    NoticeStatus.DRAFT,
                    1L
            );
            Notice notice = Notice.create(command);

            // when
            notice.publish();

            // then
            assertThat(notice.getStatus()).isEqualTo(NoticeStatus.PUBLISHED);
        }

        @Test
        @DisplayName("RESERVED -> PUBLISHED 상태 변경")
        void publishFromReserved() {
            // given
            NoticeCommand.Create command = new NoticeCommand.Create(
                    "제목",
                    "내용",
                    LocalDateTime.now().plusDays(1),
                    NoticeStatus.PUBLISHED,
                    1L
            );
            Notice notice = Notice.create(command);

            // when
            notice.publish();

            // then
            assertThat(notice.getStatus()).isEqualTo(NoticeStatus.PUBLISHED);
        }
    }
}
