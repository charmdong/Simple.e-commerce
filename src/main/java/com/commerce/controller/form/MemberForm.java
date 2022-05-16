package com.commerce.controller.form;

import com.commerce.domain.Address;
import com.commerce.dto.member.MemberDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberForm {

    private String id;
    private String password;
    private String name;
    private Address address;

    public static MemberForm createMemberForm (MemberDto member) {
        MemberForm memberForm = new MemberForm();

        memberForm.name = member.getName();
        memberForm.address = member.getAddress();

        return memberForm;
    }
}
