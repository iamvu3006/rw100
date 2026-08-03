package com.vti.form;

import com.vti.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountSearchForm {
    private String search;
    private String username;
    private String fullName;
    private String email;
    private Role role;
}