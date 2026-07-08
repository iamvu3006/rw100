package com.vti.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account")// mapping đến bảng account trong DB
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id// đại diện cho khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto_increment
    @Column(name = "account_id")// trường này cho biết là thuộc tính này map với cột account_id trong DB
    private Integer id;

    //username varchar(50) not null unique
    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    //password varchar(255) not null
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    //email varchar(100) not null unique
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    //full_name varchar(100)
    @Column(name = "full_name", length = 100)
    private String fullName;

    // khóa ngoại: 1 account thuộc về 1 position, 1 position có thể có nhiều account (n-1)
    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "position_id")
    private Position position;
}