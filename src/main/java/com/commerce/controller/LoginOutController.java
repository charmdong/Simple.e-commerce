package com.commerce.controller;

import com.commerce.controller.form.LoginForm;
import com.commerce.dto.SessionVO;
import com.commerce.dto.member.MemberDto;
import com.commerce.service.MemberService;
import com.commerce.util.SessionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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
    @ResponseBody
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginForm form, HttpServletRequest request) throws RuntimeException {

        Map<String, Object> result = new HashMap<>();

        try {
            MemberDto memberDto = memberService.login(form.getId(), form.getPassword());

            HttpSession session = request.getSession();
            SessionVO loginInfo = new SessionVO();
            loginInfo.setId(memberDto.getId());
            loginInfo.setRole(memberDto.getRole());

            log.info("loginInfo={}", loginInfo);

            session.setMaxInactiveInterval(60 * 60 * 30);
            session.setAttribute(SessionUtils.LOGIN_SESSION, loginInfo);

            result.put("login", true);
        }
        // 패스워드 불일치
        catch (IllegalArgumentException exception) {
            result.put("login", false);
            result.put("message", exception.getMessage());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/";
    }
}
