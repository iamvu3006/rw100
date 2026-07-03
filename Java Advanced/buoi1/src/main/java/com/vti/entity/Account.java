package com.vti.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Temporal;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id// đại diện cho khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto_increment
    @Column(name = "account_id")// trường này cho biết là thuộc tính này map với cột department_id trong DB
    private Integer id;

    @Column(name = "username", nullable = false, unique = true, length = 100)//
    private String username;

    @Column(name = "full_name", nullable = false, unique = true, length = 100)//
    private String fullName;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    // cấu hình khóa ngoại
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department dep;

    @Column(name = "create_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;



    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", dep=" + dep +
                ", createdDate=" + createdDate +
                '}';
    }
}