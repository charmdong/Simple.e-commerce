package com.commerce.controller.form;

import com.commerce.domain.Address;
import com.commerce.domain.Role;
import com.commerce.vo.member.MemberVO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberForm {

    private String id;
    private String password;
    private String name;
    private Role role;
    private String city;
    private String street;
    private String zipcode;
    private String address;

    public static MemberForm createMemberForm (MemberVO member) {
        MemberForm memberForm = new MemberForm();

        memberForm.name = member.getName();
        Address address = member.getAddress();
        memberForm.city = address.getCity();
        memberForm.street = address.getStreet();
        memberForm.zipcode = address.getZipcode();
        memberForm.address = address.fullAddress();

        return memberForm;
    }
}
