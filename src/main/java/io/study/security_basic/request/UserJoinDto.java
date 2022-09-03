package io.study.security_basic.request;

import io.study.security_basic.Entity.User;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserJoinDto {
    private String username;
    private String password;
    private String email;

    @Builder
    public UserJoinDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User toEntity() {
        return User.builder()
                .username(this.username)
                .email(this.email)
                .password(this.password)
                .build();
    }

    public void encodePassword(String pwd) {
        this.password = pwd;
    }
}
