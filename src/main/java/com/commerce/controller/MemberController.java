package com.commerce.controller;

import com.commerce.controller.form.MemberForm;
import com.commerce.domain.Member;
import com.commerce.dto.SessionVO;
import com.commerce.dto.member.MemberDto;
import com.commerce.service.MemberService;
import com.commerce.util.SessionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private static Map<String, String> roles = new HashMap<>();

    @GetMapping("/join")
    public String joinForm (Model model) {
        model.addAttribute("memberForm", new MemberForm());

        roles.put("USER", "일반");
        roles.put("SALE", "판매자");
        model.addAttribute("roles", roles);

        return "members/createMemberForm";
    }

    @PostMapping("/join")
    public String join (MemberForm form) {
        log.info("memberForm={}", form);

        Member member = Member.createMember(form);
        memberService.joinMember(member);

        return "redirect:/";
    }

    @GetMapping
    public String memberList (Model model) {
        model.addAttribute("members", memberService.findAllMember());
        return "members/memberList";
    }

    @GetMapping("/detail")
    public String detail (HttpSession session, Model model) throws RuntimeException {
        SessionVO loginInfo = (SessionVO) session.getAttribute(SessionUtils.LOGIN_SESSION);
        String id = loginInfo.getId();

        MemberDto member = memberService.findMember(id);
        model.addAttribute("member", member);

        return "members/memberDetail";
    }

    @GetMapping("/edit")
    public String updateMemberForm(HttpSession session, Model model) throws RuntimeException {
        SessionVO loginInfo = (SessionVO) session.getAttribute(SessionUtils.LOGIN_SESSION);
        String id = loginInfo.getId();
        MemberDto member = memberService.findMember(id);

        MemberForm memberForm = MemberForm.createMemberForm(member);
        model.addAttribute("form", memberForm);

        return "members/updateMemberForm";
    }

    @PostMapping("/edit")
    public String updateMember (HttpSession session, MemberForm form) throws RuntimeException {
        SessionVO loginInfo = (SessionVO) session.getAttribute(SessionUtils.LOGIN_SESSION);
        String id = loginInfo.getId();
        memberService.updateMember(id, form);

        return "members/detail";
    }

    @PostMapping("/delete")
    public String deleteMember (HttpSession session, @RequestParam String password) throws RuntimeException {
        SessionVO loginInfo = (SessionVO) session.getAttribute(SessionUtils.LOGIN_SESSION);
        String id = loginInfo.getId();
        memberService.deleteMember(id, password);

        return "redirect:/";
    }
}
