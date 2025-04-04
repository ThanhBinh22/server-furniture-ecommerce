package com.thesis.serverfurnitureecommerce.presentation.requestv2;

import com.thesis.serverfurnitureecommerce.common.enums.StatusOrderEnum;
import java.util.List;
import java.util.Set;

public record OrderRequest(
        Long id,
        Double totalAmount,
        StatusOrderEnum status,
        String payment,
        String message,
        Long userId,
        Long addressId,
        List<OrderItemRequest> orderItemRequests,
        Set<Long> promotionIds
) {}
