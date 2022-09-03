package io.study.security_basic.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // 스프링 필터 체인에 SecurityConfig(필터) 등록
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // 특정 메서드에만 Secure로 적용 가능
@RequiredArgsConstructor
public class SecurityConfig {
    private final DefaultOAuth2UserService oauth2Service;

//    @Bean
//    public BCryptPasswordEncoder encodePassword() {
//        return new BCryptPasswordEncoder();
//    }
    // 순환참조로 인해 분리

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**", "/test/**").authenticated()
                .antMatchers("/manager/**").access("hasRole('ADMIN') or hasRole('MANAGER')")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/form/**", "/").permitAll()
                .anyRequest().denyAll() // 화이트리스트
                        .and().formLogin()
                .loginPage("/form/login")
                        .loginProcessingUrl("/login") // /login 호출 시 시큐리티가 로그인진행
                                .defaultSuccessUrl("/") // 로그인 성공시 이동할 페이지
                .and()
                .oauth2Login()
                .loginPage("/form/login")
                .userInfoEndpoint()
                .userService(oauth2Service) // 구글 로그인 후 후처리 로직
        ;

        /*
        * Oauth 2 Logic
        * 로그인 성공 시 구글 Oauth 서버에서
        * Access Token 발급하여 클라이언트(서버)로 전달
        * 클라이언트(서버)는 Access Token을 받아서 사용자 프로필 정보에 접근하여 가입 진행
        * 추가 정보 필요할 시 별도 로직 수행
       * */

        http.csrf()
                .disable();

        return http.build();
    }
}
