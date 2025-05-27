package com.thesis.serverfurnitureecommerce.presentation.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Deprecated
public class OrderItemRequest {
    Long id;
    Integer quantity;
    Integer price;
    Integer productId;
    Long orderId;
}
