package io.study.security_basic.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentDto {
    @Getter
    @Builder
    public static class ReqeustPost {
        private String content;
    }

    @Getter
    @Builder
    public static class Response {
        private Long userId;
        private Long commentId;
        private String content;
    }
}
