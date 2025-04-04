package com.thesis.serverfurnitureecommerce.domain.requestv2;

import jakarta.validation.constraints.NotBlank;

public record LogoutRequest(
        @NotBlank(message = "PARAMETER_MISSING") String token) {
}
