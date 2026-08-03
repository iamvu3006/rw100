package com.vti.form;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateForm {
    @NotEmpty(message = "Danh sách món ăn không được để trống")
    @Valid
    private List<OrderItemForm> orderItems;
}
