package com.thesis.serverfurnitureecommerce.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

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
    List<ImageDTO> images;
    CategoryDTO category;
    SupplierDTO supplier;
}
