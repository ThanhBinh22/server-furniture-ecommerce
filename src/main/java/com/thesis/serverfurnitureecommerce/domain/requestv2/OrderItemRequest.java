package com.thesis.serverfurnitureecommerce.domain.requestv2;

public record OrderItemRequest(
        Long id,
        Integer quantity,
        Integer price,
        Integer productId,
        Long orderId
) {}
