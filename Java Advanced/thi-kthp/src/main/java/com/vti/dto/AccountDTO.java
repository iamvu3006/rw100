package com.vti.dto;

import com.vti.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {
    private Integer id;
    private String username;
    private String fullName;
    private String email;
    private Role role;
    private String avatarUrl;
    private LocalDateTime createdDate;
}