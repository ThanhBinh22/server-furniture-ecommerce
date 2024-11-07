package com.thesis.serverfurnitureecommerce.domain.response;

import com.thesis.serverfurnitureecommerce.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * A response object for login requests.
 *
 * This class encapsulates the data returned after a successful login,
 * including an authentication token and its expiration time.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class LoginResponse {

    /**
     * The authentication token generated upon successful login.
     * This token should be included in subsequent requests to
     * access protected resources.
     */
    private String accessToken;
    private String refreshToken;
    private UserDTO user;

    /**
     * The duration in seconds until the token expires.
     * This indicates how long the token is valid before
     * requiring the user to log in again.
     */
    private long expiresIn;
}
