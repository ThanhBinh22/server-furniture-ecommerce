package com.thesis.serverfurnitureecommerce.domain.requestv2;

import jakarta.validation.constraints.NotBlank;

public record AccountVerifyRequest(
        @NotBlank(message = "PARAMETER_MISSING") String email,
        @NotBlank(message = "PARAMETER_MISSING") String otp
) {}
