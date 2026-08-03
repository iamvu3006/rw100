package com.vti.dto;

import com.vti.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Integer id;
    private Integer tableId;
    private String tableNumber;
    private Integer userId;
    private String userName;
    private LocalDateTime createdDate;
    private LocalDateTime paymentDate;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private List<OrderItemDTO> orderItems;
}
