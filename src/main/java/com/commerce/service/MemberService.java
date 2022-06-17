package com.commerce.service;

import com.commerce.controller.form.MemberForm;
import com.commerce.domain.Member;
import com.commerce.vo.member.MemberVO;
import com.commerce.repository.MemberRepository;
import com.commerce.util.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberVO login (String id, String password) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.USER_NOT_FOUND));

        if (!member.getPassword().equals(password)) {
            throw new IllegalArgumentException(ExceptionUtils.INCORRECT_PWD);
        }

        return new MemberVO(member);
    }

    @Transactional(readOnly = true)
    public MemberVO findMember (String id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.USER_NOT_FOUND));
        return new MemberVO(member);
    }

    @Transactional(readOnly = true)
    public List<MemberVO> findAllMember () {
        return memberRepository.findAll().stream().map(MemberVO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Boolean isValidId (String id) {
        return memberRepository.findById(id).isEmpty();
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
