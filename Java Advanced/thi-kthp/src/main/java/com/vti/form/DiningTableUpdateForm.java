package com.vti.form;

import com.vti.enums.TableStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiningTableUpdateForm {
    @NotBlank(message = "Mã/Số bàn không được để trống")
    private String tableNumber;

    @Min(value = 1, message = "Sức chứa bàn phải từ 1 người trở lên")
    private Integer capacity;

    private TableStatus status;
}
