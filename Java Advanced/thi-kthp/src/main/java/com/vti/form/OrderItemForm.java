package com.vti.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemForm {
    @NotNull(message = "Dish ID không được để trống")
    private Integer dishId;

    @NotNull(message = "Số lượng món ăn không được để trống")
    @Min(value = 1, message = "Số lượng món ăn phải lớn hơn hoặc bằng 1")
    private Integer quantity;
}
