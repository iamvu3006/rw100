package com.vti.dto;

import com.vti.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Integer id;
    private String fullName;
    private String username;
    private String departmentName;
    private String positionName;

    //constructor
    public AccountDTO(Account account) {
        this.id = account.getId();
        this.fullName = account.getFullName();
        this.username = account.getUsername();
        if (Objects.nonNull(account.getDepartment())) {
            this.departmentName = account.getDepartment().getName();
        }
        if (Objects.nonNull(account.getPosition())) {
            this.positionName = account.getPosition().getName().name();
        }
    }
}