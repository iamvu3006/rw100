package com.vti.dto;

import com.vti.enums.TableStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiningTableDTO {
    private Integer id;
    private String tableNumber;
    private Integer capacity;
    private TableStatus status;
}
