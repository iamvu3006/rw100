package com.vti.entity;

import com.vti.enums.TableStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dining_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiningTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
    private Integer id;

    @Column(name = "table_number", nullable = false, unique = true, length = 50)
    private String tableNumber;

    @Column(name = "capacity")
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TableStatus status;
}
