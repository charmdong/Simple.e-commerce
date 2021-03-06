package com.commerce.vo;

import com.commerce.domain.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class SessionVO {

    private String id;
    private Role role;

    public SessionVO (String id, Role role) {
        this.id = id;
        this.role = role;
    }
}
