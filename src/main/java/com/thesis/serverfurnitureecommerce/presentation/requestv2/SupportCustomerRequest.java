package com.thesis.serverfurnitureecommerce.presentation.requestv2;

public record SupportCustomerRequest(String title,
                                     String email,
                                     String message,
                                     String feedback,
                                     Boolean isSolve) {
}
