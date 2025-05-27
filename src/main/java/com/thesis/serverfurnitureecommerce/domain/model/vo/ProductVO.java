package com.thesis.serverfurnitureecommerce.domain.model.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVO {
    Integer id;
    String name;
    String price;
    Integer stock;
    String description;
    Short isActive;
    List<ImageVO> images;
    CategoryVO category;
    SupplierVO supplier;
    List<ReviewVO> reviewVO;

    public static ProductVO create() {
        return new ProductVO();
    }
}
