package com.thesis.serverfurnitureecommerce.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * A request class for updating a user's password.
 * <p>
 * This class encapsulates the information required to update
 * a user's password. It includes the user's email for verification
 * and the new password that the user wants to set.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewPasswordRequest {

    /**
     * The email address of the user requesting the password update.
     * This field must not be blank.
     */
    @NotBlank(message = "PARAMETER_MISSING")
    String email;

    /**
     * The new password that the user wants to set.
     * This field must not be blank.
     */
    @NotBlank(message = "PARAMETER_MISSING")
    String newPassword;
}
