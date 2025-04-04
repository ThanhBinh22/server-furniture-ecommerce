package com.thesis.serverfurnitureecommerce.domain.responsev2;

import com.thesis.serverfurnitureecommerce.model.dto.UserDTO;
import lombok.Builder;

@Builder
public record LoginResponse(
        String accessToken,
        String refreshToken,
        UserDTO user,
        long expiresIn
) {}

