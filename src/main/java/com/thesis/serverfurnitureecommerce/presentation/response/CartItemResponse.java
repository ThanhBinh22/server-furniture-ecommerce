package com.thesis.serverfurnitureecommerce.presentation.response;

import com.thesis.serverfurnitureecommerce.domain.model.vo.ProductVO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemResponse {
    String id;
    ProductVO productVO;
    Integer quantity;

    public static CartItemResponse create() {
        return new CartItemResponse();
    }
}
