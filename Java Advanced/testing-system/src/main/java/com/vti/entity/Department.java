package com.vti.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "department")// mapping đến bảng department trong DB
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id// đại diện cho khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto_increment
    @Column(name = "department_id")// trường này cho biết là thuộc tính này map với cột department_id trong DB
    private Integer id;

    //department_name varchar(100) not null unique
    @Column(name = "department_name", nullable = false, unique = true, length = 100)//
    private String name;
}