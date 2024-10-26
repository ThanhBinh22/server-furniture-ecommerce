package com.thesis.serverfurnitureecommerce.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * A request class for user authentication.
 * <p>
 * This class contains the necessary fields for a user to authenticate
 * themselves, including a username and a password. It ensures that both
 * fields are provided and not blank.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {

    /**
     * The username of the user attempting to authenticate.
     * This field cannot be blank.
     */
    @NotBlank(message = "PARAMETER_MISSING")
    String username;

    /**
     * The password of the user attempting to authenticate.
     * This field cannot be blank.
     */
    @NotBlank(message = "PARAMETER_MISSING")
    String password;
}
