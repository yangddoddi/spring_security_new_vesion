package io.study.security_basic.controller;

import io.study.security_basic.Entity.User;
import io.study.security_basic.request.UserJoinDto;
import io.study.security_basic.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
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
    public String user() {

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
    @GetMapping("/ingo")
    public String info() {
        return "개인정보 히히";
    }
}
