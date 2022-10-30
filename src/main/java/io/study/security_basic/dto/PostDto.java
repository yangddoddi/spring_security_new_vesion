package io.study.security_basic.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class PostDto {
    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public final static class RequestPost {
        private String title;
        private String content;
        private String categoryName;
        private List<String> tags;
    }

    @Getter
    @Builder
    public final static class ResponseDetail  {
        private Long postId;
        private String title;
        private String content;
        private String category;
        private Long view;
        private UserDto.UserInfoForResponsePost userInfo;
        private List<TagDto.Response> tags;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;
    }
}
