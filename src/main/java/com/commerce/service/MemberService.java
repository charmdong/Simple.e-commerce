package com.commerce.service;

import com.commerce.controller.form.MemberForm;
import com.commerce.domain.Member;
import com.commerce.dto.member.MemberDto;
import com.commerce.repository.MemberRepository;
import com.commerce.util.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDto login (String id, String password) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.USER_NOT_FOUND));

        if (!member.getPassword().equals(password)) {
            throw new IllegalArgumentException(ExceptionUtils.INCORRECT_PWD);
        }

        return new MemberDto(member);
    }

    @Transactional(readOnly = true)
    public MemberDto findMember (String id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.USER_NOT_FOUND));
        return new MemberDto(member);
    }

    public void joinMember (Member member) {
        memberRepository.save(member);
    }

    public void updateMember (String id, MemberForm form) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.USER_NOT_FOUND));
        member.changeInfo(form);
    }

    public void deleteMember (String id, String password) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.USER_NOT_FOUND));

        if (member.getPassword().equals(password)) {
            memberRepository.delete(member);
        }
        else {
            throw new IllegalArgumentException(ExceptionUtils.INCORRECT_PWD);
        }
    }

}
