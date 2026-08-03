package com.vti.form;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateForm {
    @NotNull(message = "Bàn ăn (tableId) không được để trống")
    private Integer tableId;

    @NotEmpty(message = "Danh sách món ăn không được để trống")
    @Valid
    private List<OrderItemForm> orderItems;
}
