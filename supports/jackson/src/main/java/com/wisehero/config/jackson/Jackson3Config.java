package com.wisehero.config.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import tools.jackson.core.StreamWriteFeature;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.cfg.EnumFeature;


@Configuration
public class Jackson3Config {

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    public JsonMapperBuilderCustomizer jacksonCustomizer() {
        return builder -> {
            builder.findAndAddModules();

            // JSON 출력 시 사람이 읽기 쉬운 pretty-print 형태로 들여쓰기 적용
            builder.enable(SerializationFeature.INDENT_OUTPUT);

            // BigDecimal을 지수 형식(e.g. 1E+2) 대신 일반 숫자 형식(100)으로 직렬화
            builder.enable(StreamWriteFeature.WRITE_BIGDECIMAL_AS_PLAIN);

            // JSON Generator가 객체·배열을 자동으로 close하도록 설정
            builder.enable(StreamWriteFeature.AUTO_CLOSE_CONTENT);

            // 직렬화 시 알 수 없는 property가 있더라도 무시하고 진행
            builder.enable(StreamWriteFeature.IGNORE_UNKNOWN);

            // 빈 문자열("")을 null 값으로 간주하여 역직렬화
            builder.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

            // JSON 배열이 와야 하는 필드에 단일 값이 와도 자동으로 단일-element 배열로 처리
            // 예: "value" → ["value"]
            builder.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

            // Enum 역직렬화 시 Enum.name() 대신 Enum.toString() 값을 기준으로 매핑
            builder.enable(EnumFeature.READ_ENUMS_USING_TO_STRING);

            // Enum 직렬화 시 Enum.name() 대신 Enum.toString() 값을 출력
            builder.enable(EnumFeature.WRITE_ENUMS_USING_TO_STRING);

            // 필드가 없는 객체라도 직렬화 시 오류 발생시키지 않음
            builder.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

            // @JsonIgnore된 필드가 JSON에 포함되어 있어도 오류 발생시키지 않음
            builder.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);

            // JSON에 매핑되지 않는 필드가 있어도 예외를 발생시키지 않음
            // 외부 API 응답 파싱 시 자주 사용됨
            builder.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            builder.changeDefaultPropertyInclusion(incl -> incl.withValueInclusion(JsonInclude.Include.NON_NULL))
                    .changeDefaultPropertyInclusion(incl -> incl.withContentInclusion(JsonInclude.Include.NON_NULL));
        };
    }
}


