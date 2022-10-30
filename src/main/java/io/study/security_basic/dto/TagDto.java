package io.study.security_basic.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TagDto {
    @Builder
    @Getter
    public static class Response {
        private Long id;
        private String name;
    }

    @Builder
    @Getter
    public static class Request {
        private String tagname;
    }
}
