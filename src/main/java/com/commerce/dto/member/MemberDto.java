package com.commerce.dto.member;

import com.commerce.domain.Address;
import com.commerce.domain.Member;
import com.commerce.domain.Role;
import lombok.Data;

@Data
public class MemberDto {

    private String id;
    private String name;
    private Address address;
    private Role role;

    public MemberDto (Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.address = member.getAddress();
        this.role = member.getRole();
    }
}
