package com.vti.form;

import com.vti.enums.DishStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishFilterForm {
    private String search;
    private Integer categoryId;
    private DishStatus status;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}
