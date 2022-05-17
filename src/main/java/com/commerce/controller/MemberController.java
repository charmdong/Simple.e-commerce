package com.commerce.controller;

import com.commerce.controller.form.MemberForm;
import com.commerce.domain.Member;
import com.commerce.dto.SessionVO;
import com.commerce.dto.member.MemberDto;
import com.commerce.service.MemberService;
import com.commerce.util.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm () {
        return "members/createMemberForm";
    }

    @PostMapping("/join")
    public String join (MemberForm form) {
        Member member = Member.createMember(form);
        memberService.joinMember(member);

        return "redirect:/";
    }

    @GetMapping("/detail")
    public String detail (HttpSession session, Model model) {
        SessionVO loginInfo = (SessionVO) session.getAttribute(SessionUtils.LOGIN_SESSION);
        String id = loginInfo.getId();
        MemberDto member = memberService.findMember(id);
        model.addAttribute("member", member);

        return "members/detail";
    }

    @GetMapping("/edit")
    public String updateMemberForm(HttpSession session, Model model) {
        SessionVO loginInfo = (SessionVO) session.getAttribute(SessionUtils.LOGIN_SESSION);
        String id = loginInfo.getId();
        MemberDto member = memberService.findMember(id);

        MemberForm memberForm = MemberForm.createMemberForm(member);
        model.addAttribute("form", memberForm);

        return "members/updateMemberForm";
    }

    @PostMapping("/edit")
    public String updateMember (HttpSession session, MemberForm form) {
        SessionVO loginInfo = (SessionVO) session.getAttribute(SessionUtils.LOGIN_SESSION);
        String id = loginInfo.getId();
        memberService.updateMember(id, form);

        return "members/detail";
    }

    @PostMapping("/delete")
    public String deleteMember (HttpSession session, @RequestParam String password) {
        SessionVO loginInfo = (SessionVO) session.getAttribute(SessionUtils.LOGIN_SESSION);
        String id = loginInfo.getId();
        memberService.deleteMember(id, password);

        return "redirect:/";
    }
}
