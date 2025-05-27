package com.thesis.serverfurnitureecommerce.domain.model.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemVO {
    Integer quantity;
    Integer price;
    Integer productId;

    public static OrderItemVO create() {
        return new OrderItemVO();
    }
}
