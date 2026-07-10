package com.vti.dto;

import com.vti.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Integer id;
    private String username;
    private String fullName;
    private String departmentName;//department   Name
    private String positionName;//position       Name
    //.. 20 thuộc tính

//    public AccountDTO(Account account) {
//        this.id = account.getId();
//        this.fullName = account.getFullName();
//        this.username = account.getUsername();
//        this.departmentName = account.getDepartment().getName();
//        this.positionName = account.getPosition().getName().name();
//        // = 20 lần
//    }
}