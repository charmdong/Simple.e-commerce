package com.commerce.dto;

import com.commerce.domain.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SessionVO {

    private String id;
    private Role role;

    public SessionVO (String id, Role role) {
        this.id = id;
        this.role = role;
    }
}
