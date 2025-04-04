package com.thesis.serverfurnitureecommerce.domain.responsev2;

import lombok.Builder;

@Builder
public record PaymentResponse(
        Long orderID,
        String status,
        String paymentMethod,
        String urlPayment
) {}
