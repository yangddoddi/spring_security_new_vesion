package io.study.security_basic.controller;

import io.study.security_basic.Entity.User;
import io.study.security_basic.auth.PrincipalDetails;
import io.study.security_basic.request.UserJoinDto;
import io.study.security_basic.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequiredArgsConstructor
public class IndexController {
    private final UserService userService;

    @GetMapping({"/",""})
    public String getIndex() {
        //리졸버 설정: prfix: templates, suffix: .mustache
        return "index";
    }

    @GetMapping("/user")
    @ResponseBody
    public String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        log.info("pricnipalDetauls = {}", principalDetails.getUser());
        return "user";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    @ResponseBody
    public String manager() {
        return "manager";
    }

    @GetMapping("/form/login")
    public String getLogin() {
        return "loginForm";
    }

    @GetMapping("/form/join")
    public String getJoin() {
        return "joinForm";
    }

    @PostMapping("/form/login")
    public String postLogin() {
        return "ok";
    }

    @PostMapping("/form/join")
    public String postJoin(UserJoinDto user) {
        userService.join(user);
        return "redirect:/loginForm";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    @ResponseBody
    public String info() {
        return "개인정보 히히";
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    @GetMapping("/important")
    @ResponseBody
    public String important() {
        return "중요한 페이지 히히";
    }

    @GetMapping("/test/login")
    @ResponseBody
    public String test(Authentication authentication,
                       @AuthenticationPrincipal PrincipalDetails userDetails) {
        log.info("authentication.principal = {}", authentication.getPrincipal());

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal(); // userDetails
        User user = principalDetails.getUser();
        log.info("user ={}",user);

        log.info("userDetaios.getUsername = {}",userDetails.getUser());

        return "test Ok";
    }

    @GetMapping("/test/oauth-login")
    @ResponseBody
    public String testOauth(Authentication authentication,
                            @AuthenticationPrincipal OAuth2User oAuth2User2) {
        log.info("authentication.principal = {}", authentication.getPrincipal());

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal(); // userDetails
        log.info("Oauth2User.getAttributes ={}",oAuth2User.getAttributes());

        log.info("OAuth2User2.getAttributes ={}",oAuth2User2.getAttributes());
        return "test Ok";
    }
}
