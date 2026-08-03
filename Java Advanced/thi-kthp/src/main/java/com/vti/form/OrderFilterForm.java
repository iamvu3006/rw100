package com.vti.form;

import com.vti.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderFilterForm {
    private Integer tableId;
    private OrderStatus status;
    private String search;
}
