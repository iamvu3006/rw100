package com.vti.form;

import com.vti.enums.DishStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishCreateForm {
    @NotBlank(message = "Tên món ăn không được để trống")
    private String name;

    @NotNull(message = "Giá món ăn không được để trống")
    @Min(value = 0, message = "Giá món ăn phải lớn hơn hoặc bằng 0")
    private BigDecimal price;

    private String description;

    private String imageUrl;

    @NotNull(message = "Trạng thái món ăn không được để trống")
    private DishStatus status;

    @NotNull(message = "Category ID không được để trống")
    private Integer categoryId;
}
