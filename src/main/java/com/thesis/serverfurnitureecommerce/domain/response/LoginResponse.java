package com.thesis.serverfurnitureecommerce.domain.response;

import com.thesis.serverfurnitureecommerce.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private UserDTO user;
    private long expiresIn;
}
