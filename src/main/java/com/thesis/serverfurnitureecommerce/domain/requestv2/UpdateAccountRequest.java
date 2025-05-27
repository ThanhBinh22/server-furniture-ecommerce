package com.thesis.serverfurnitureecommerce.domain.requestv2;

public record UpdateAccountRequest(String username,
        String fullName,
        String email,
        String phone) {
}
