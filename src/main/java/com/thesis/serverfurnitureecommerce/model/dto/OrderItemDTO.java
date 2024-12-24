package com.thesis.serverfurnitureecommerce.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemDTO {
    Long id;
    Integer quantity;
    Integer price;
    Integer productId;

    public static OrderItemDTO create() {
        return new OrderItemDTO();
    }
}
