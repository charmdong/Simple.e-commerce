package com.commerce.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginForm {

    private String id;
    private String password;

    public LoginForm (String id, String password) {
        this.id = id;
        this.password = password;
    }
}
