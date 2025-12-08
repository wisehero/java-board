package com.wisehero.javaboard.notice;

import com.wisehero.javaboard.support.error.CoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("NoticeStatus Enum 테스트")
class NoticeStatusTest {

    @Test
    @DisplayName("DRAFT 문자열로 변환 성공")
    void fromStringDraft() {
        // when
        NoticeStatus status = NoticeStatus.fromString("DRAFT");

        // then
        assertThat(status).isEqualTo(NoticeStatus.DRAFT);
        assertThat(status.getDescription()).isEqualTo("임시 저장");
    }

    @Test
    @DisplayName("RESERVED 문자열로 변환 성공")
    void fromStringReserved() {
        // when
        NoticeStatus status = NoticeStatus.fromString("RESERVED");

        // then
        assertThat(status).isEqualTo(NoticeStatus.RESERVED);
        assertThat(status.getDescription()).isEqualTo("예약");
    }

    @Test
    @DisplayName("PUBLISHED 문자열로 변환 성공")
    void fromStringPublished() {
        // when
        NoticeStatus status = NoticeStatus.fromString("PUBLISHED");

        // then
        assertThat(status).isEqualTo(NoticeStatus.PUBLISHED);
        assertThat(status.getDescription()).isEqualTo("게시");
    }

    @ParameterizedTest
    @ValueSource(strings = {"draft", "Draft", "dRaFt"})
    @DisplayName("대소문자 구분 없이 변환 성공")
    void fromStringCaseInsensitive(String input) {
        // when
        NoticeStatus status = NoticeStatus.fromString(input);

        // then
        assertThat(status).isEqualTo(NoticeStatus.DRAFT);
    }

    @Test
    @DisplayName("잘못된 문자열로 변환 시 예외 발생")
    void fromStringInvalid() {
        // when & then
        assertThatThrownBy(() -> NoticeStatus.fromString("INVALID"))
                .isInstanceOf(CoreException.class)
                .hasMessageContaining("Invalid notice status: INVALID");
    }

    @Test
    @DisplayName("null 문자열로 변환 시 예외 발생")
    void fromStringNull() {
        // when & then
        assertThatThrownBy(() -> NoticeStatus.fromString(null))
                .isInstanceOf(CoreException.class);
    }

    @Test
    @DisplayName("빈 문자열로 변환 시 예외 발생")
    void fromStringEmpty() {
        // when & then
        assertThatThrownBy(() -> NoticeStatus.fromString(""))
                .isInstanceOf(CoreException.class)
                .hasMessageContaining("Notice status cannot be null or empty");
    }
}
