package com.thesis.serverfurnitureecommerce.domain.requestv2;

import jakarta.validation.constraints.NotBlank;

public record NewPasswordRequest(
        @NotBlank(message = "PARAMETER_MISSING") String email,
        @NotBlank(message = "PARAMETER_MISSING") String password) {
}
