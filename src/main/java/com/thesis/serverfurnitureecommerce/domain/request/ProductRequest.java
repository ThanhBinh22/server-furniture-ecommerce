package com.thesis.serverfurnitureecommerce.domain.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    Integer id;
    String name;
    Integer categoryID;
    String description;
    Integer price;
    Integer stock;
    String image;
    Integer supplierID;
}
