package com.vti.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Department")
public class Department {
    @Id //đại diện cho khóa chính
    @GeneratedValue(strategy=GenerationType.IDENTITY) //auto increment
    @Column(name = "department_id") //trường này cho biết là thuộc tính này map với cột department_id trong DB
    private Integer id;

    @Column(name = "department_name", length = 100, nullable = false, unique = true) //trường này cho biết là thuộc tính này map với cột department_name trong DB
    private String name;

}

