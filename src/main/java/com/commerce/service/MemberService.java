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
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDto login (String id, String password) {
        Member member = getMember(id);

        if (!member.getPassword().equals(password)) {
            throw new IllegalArgumentException(ExceptionUtils.INCORRECT_PWD);
        }

        return new MemberDto(member);
    }

    @Transactional(readOnly = true)
    public MemberDto findMember (String id) {
        Member member = getMember(id);
        return new MemberDto(member);
    }

    public void joinMember (Member member) {
        memberRepository.save(member);
    }

    public void updateMember (String id, MemberForm form) {
        Member member = getMember(id);
        member.changeInfo(form);
    }

    public void deleteMember (String id, String password) {
        Member member = getMember(id);

        if (member.getPassword().equals(password)) {
            memberRepository.delete(member);
        }
        else {
            throw new IllegalArgumentException(ExceptionUtils.INCORRECT_PWD);
        }
    }

    private Member getMember (String id) {
        Optional<Member> findMember = memberRepository.findById(id);

        if (findMember.isEmpty()) {
            throw new NoSuchElementException(ExceptionUtils.USER_NOT_FOUND);
        }

        return findMember.get();
    }
}
