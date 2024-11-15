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
    String price;
    Integer stock;
    String description;
    Short isActive;
    List<ImageDTO> images;
    CategoryDTO category;
    SupplierDTO supplier;
    List<ReviewDTO> reviewDTO;
}
