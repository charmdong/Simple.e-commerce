package com.commerce.domain;

import com.commerce.controller.form.MemberForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;
    private String password;
    private String name;
    private Role role;

    @Embedded
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public static Member createMember(MemberForm form) {
        Member member = new Member();

        member.id = form.getId();
        member.password = form.getPassword();
        member.name = form.getName();
        member.address = form.getAddress();
        member.role = Role.USER;

        return member;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeInfo(MemberForm form) {
        if (StringUtils.hasText(form.getName())) {
            this.name = form.getName();
        }

        if (form.getAddress() != null) {
            this.address = form.getAddress();
        }
    }
}
