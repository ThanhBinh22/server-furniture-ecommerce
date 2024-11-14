package com.thesis.serverfurnitureecommerce.domain.response;

import com.thesis.serverfurnitureecommerce.model.dto.ProductDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemResponse {
    String id;
    ProductDTO productDTO;
    Integer quantity;

    public static CartItemResponse create() {
        return new CartItemResponse();
    }
}
