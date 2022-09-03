package io.study.security_basic.auth;

// 로그인 진행 완료 -> Security ContextHolder(세션) 생성
// SecurityContextHolder -> Authentication -> UserDetails

/*
* 로그인 진행 완료 -> Security ContextHolder(세션) 생성
* SecurityContextHolder -> Authentication -> UserDetails
* Session(Authentication(UserDeatils))
* */


import io.study.security_basic.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {
    private User user;
    private final int TIME_LIMIT = 2;

    public PrincipalDetails(User user) {
        this.user=user;
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
}
