package com.thesis.serverfurnitureecommerce.domain.requestv2;

public record SupportCustomerRequest(String title,
                                     String email,
                                     String message,
                                     String feedback,
                                     Boolean isSolve) {
}
