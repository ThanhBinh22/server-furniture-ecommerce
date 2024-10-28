package com.thesis.serverfurnitureecommerce.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO {
    Integer id;
    String name;
    Integer price;
    Integer stock;
    String description;
    Short isActive;
    String image;
    CategoryDTO category;
    SupplierDTO supplier;
}
