package com.thesis.serverfurnitureecommerce.presentation.requestv2;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
        @NotBlank(message = "PARAMETER_MISSING") String username,
        @NotBlank(message = "PARAMETER_MISSING") String password) {
}
