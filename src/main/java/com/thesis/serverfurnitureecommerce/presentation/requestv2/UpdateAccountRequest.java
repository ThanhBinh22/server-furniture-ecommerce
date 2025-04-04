package com.thesis.serverfurnitureecommerce.presentation.requestv2;

public record UpdateAccountRequest(String username,
        String fullName,
        String email,
        String phone) {
}
