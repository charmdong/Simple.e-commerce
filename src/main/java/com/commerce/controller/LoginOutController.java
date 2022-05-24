package com.commerce.controller;

import com.commerce.controller.form.LoginForm;
import com.commerce.dto.SessionVO;
import com.commerce.dto.member.MemberDto;
import com.commerce.service.MemberService;
import com.commerce.util.SessionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginOutController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(LoginForm form, HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();

        MemberDto memberDto = memberService.login(form.getId(), form.getPassword());

        SessionVO loginInfo = new SessionVO();
        loginInfo.setId(memberDto.getId());
        loginInfo.setRole(memberDto.getRole());

        log.info("loginInfo={}", loginInfo);

        session.setMaxInactiveInterval(60 * 60 * 30);
        session.setAttribute(SessionUtils.LOGIN_SESSION, loginInfo);

        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/";
    }
}
