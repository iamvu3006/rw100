package com.vti.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDTO {
    private Integer id;
    private Integer dishId;
    private String dishName;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subTotal;
}
