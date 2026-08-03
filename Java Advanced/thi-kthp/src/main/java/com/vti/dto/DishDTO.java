package com.vti.dto;

import com.vti.enums.DishStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishDTO {
    private Integer id;
    private String name;
    private BigDecimal price;
    private String description;
    private String imageUrl;
    private DishStatus status;
    private Integer categoryId;
    private String categoryName;
}
