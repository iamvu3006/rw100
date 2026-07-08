package com.vti.entity;

import com.vti.enums.ArticlePositionNameConverter;
import com.vti.enums.PositionName;
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

    //    @Enumerated(EnumType.STRING)// String ORDINAL
    @Convert(converter = ArticlePositionNameConverter.class)
    @Column(name = "position_name")//
    private PositionName name;
}