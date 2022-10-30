package io.study.security_basic.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDto {
    @Builder
    @Getter
    public static class UserInfoForResponsePost {
        private Long userId;
        private String email;
        private String username;
        private String nickname;
    }
}
