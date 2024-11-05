package com.thesis.serverfurnitureecommerce.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A request class for verifying user accounts during the account verification process.
 * <p>
 * This class contains the required fields for a user to verify their account,
 * including the email address and the OTP (One-Time Password) sent to the user.
 * </p>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountVerifyRequest {
    /**
     * The email address of the user that needs to be verified.
     * This field cannot be blank.
     */
    @NotBlank(message = "PARAMETER_MISSING")
    private String email;

    /**
     * The OTP (One-Time Password) sent to the user's email for verification.
     * This field cannot be blank.
     */
    @NotBlank(message = "PARAMETER_MISSING")
    private String otp;
}
