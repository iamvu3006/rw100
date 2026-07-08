package com.vti.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "positions")// mapping đến bảng positions trong DB ("position" là từ khóa reserved của SQL nên đổi tên bảng)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    @Id// đại diện cho khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto_increment
    @Column(name = "position_id")// trường này cho biết là thuộc tính này map với cột position_id trong DB
    private Integer id;

    //position_name varchar(100) not null unique
    @Column(name = "position_name", nullable = false, unique = true, length = 100)
    private String name;
}