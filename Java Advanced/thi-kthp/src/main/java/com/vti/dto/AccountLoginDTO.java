package com.vti.dto;

import com.vti.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountLoginDTO {
    private String token;
    private Integer id;
    private String username;
    private String fullName;
    private Role role;
}