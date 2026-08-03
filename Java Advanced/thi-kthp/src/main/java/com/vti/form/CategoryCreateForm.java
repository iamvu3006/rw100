package com.vti.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateForm {
    @NotBlank(message = "Tên danh mục không được để trống")
    private String name;

    private String description;
}
