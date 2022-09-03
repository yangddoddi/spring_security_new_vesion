package io.study.security_basic.auth;

// 로그인 진행 완료 -> Security ContextHolder(세션) 생성
// SecurityContextHolder -> Authentication -> UserDetails




import io.study.security_basic.Entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/*
 * 로그인 진행 완료 -> Security ContextHolder(세션) 생성
 * SecurityContextHolder -> Authentication -> UserDetails or OAuth2USer
 * Session(Authentication(UserDeatils, OAuth2User))
 *
 * getAttribute -> Oauth2User
 *
 * */

@Getter
public class PrincipalDetails implements UserDetails, OAuth2User {
    private final int TIME_LIMIT = 2;
    private User user;
    private Map<String, Object> attributes;

    // 일반 로그인
    public PrincipalDetails(User user) {
        this.user=user;
    }

    // OAuth 로그인
    public PrincipalDetails(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 유저 권한 획득
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add((GrantedAuthority) () -> user.getRole());
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getCreatedDate().isAfter(ChronoLocalDateTime.from(LocalDateTime.now().minusYears(TIME_LIMIT)));
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getModifiedDate().isAfter(ChronoLocalDateTime.from(LocalDateTime.now().minusYears(TIME_LIMIT)));
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getModifiedDate().isAfter(ChronoLocalDateTime.from(LocalDateTime.now().minusYears(TIME_LIMIT)));
    }

    @Override
    public boolean isEnabled() {
        return user.getModifiedDate().isAfter(ChronoLocalDateTime.from(LocalDateTime.now().minusYears(TIME_LIMIT)));
    }

    @Override
    public String getName() {
        return attributes.get("sub").toString();
    }
}
