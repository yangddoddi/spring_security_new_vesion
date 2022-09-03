package io.study.security_basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // 스프링 필터 체인에 SecurityConfig(필터) 등록
@EnableGlobalMethodSecurity(securedEnabled = true) // 특정 메서드에만 Secure로 적용 가능
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/manager/**").access("hasRole('ADMIN') or hasRole('MANAGER')")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/form/**", "/").permitAll()
                .anyRequest().denyAll()
                        .and().formLogin()
                .loginPage("/form/login")
                        .loginProcessingUrl("/login") // /login 호출 시 시큐리티가 로그인진행
                                .defaultSuccessUrl("/") // 로그인 성공시 이동할 페이지
        ;

        http.csrf()
                .disable();

        return http.build();
    }
}
