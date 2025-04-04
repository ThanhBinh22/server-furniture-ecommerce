package com.thesis.serverfurnitureecommerce.presentation.request;

import com.thesis.serverfurnitureecommerce.common.enums.StatusOrderEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderRequest {
    Long id;
    Double totalAmount;
    StatusOrderEnum status;
    String payment;
    String message;
    Long userId;
    Long addressId;
    List<OrderItemRequest> orderItemRequests;
    Set<Long> promotionIds;
}
