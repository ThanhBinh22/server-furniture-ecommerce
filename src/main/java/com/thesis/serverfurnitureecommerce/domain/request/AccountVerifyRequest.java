package com.thesis.serverfurnitureecommerce.domain.request;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountVerifyRequest {
    @NotBlank(message = "PARAMETER_MISSING")
    private String email;
    @NotBlank(message = "PARAMETER_MISSING")
    private String otp;
}
