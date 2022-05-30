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

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Embedded
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Cart> carts = new ArrayList<>();

    public static Member createMember(MemberForm form) {
        Member member = new Member();

        member.id = form.getId();
        member.password = form.getPassword();
        member.name = form.getName();
        member.address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        member.role = form.getRole();

        return member;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeInfo(MemberForm form) {
        if (StringUtils.hasText(form.getName())) {
            this.name = form.getName();
        }

        if (StringUtils.hasText(form.getCity())) {
            this.address.setCity(form.getCity());
        }

        if (StringUtils.hasText(form.getStreet())) {
            this.address.setCity(form.getStreet());
        }

        if (StringUtils.hasText(form.getZipcode())) {
            this.address.setCity(form.getZipcode());
        }
    }
}
