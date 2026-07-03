package com.vti.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "department")// mapping đến bảng department trong DB
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
public class Department {
    @Id// đại diện cho khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto_increment
    @Column(name = "department_id")// trường này cho biết là thuộc tính này map với cột department_id trong DB
    private Integer id;

    //department_name varchar(100) not null unique
    @Column(name = "department_name", nullable = false, unique = true, length = 100)//
    private String name;

//    // ko có cx dc
//    @OneToMany(mappedBy = "dep", fetch = FetchType.EAGER)
//    private List<Account> accounts;// list account thuôc department này

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}