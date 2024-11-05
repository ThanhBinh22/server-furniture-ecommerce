package com.thesis.serverfurnitureecommerce.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A request class for logging out a user.
 * <p>
 * This class encapsulates the token required to log out a user
 * from the system. The token is used to validate the logout
 * request and ensure that the session is terminated properly.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogoutRequest {

    /**
     * The token used to identify the user session for logout.
     * This token must not be blank.
     */
    @NotBlank(message = "PARAMETER_MISSING")
    private String token;
}
