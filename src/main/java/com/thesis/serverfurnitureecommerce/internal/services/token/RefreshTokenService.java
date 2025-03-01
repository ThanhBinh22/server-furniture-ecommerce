package com.thesis.serverfurnitureecommerce.internal.services.token;

import com.thesis.serverfurnitureecommerce.model.dto.UserDTO;
import com.thesis.serverfurnitureecommerce.model.entity.RefreshTokenEntity;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;

import java.util.UUID;

public interface RefreshTokenService {
    RefreshTokenEntity createRefreshToken(UserEntity user);

    RefreshTokenEntity verifyExpiration(RefreshTokenEntity token);

    String generateAccessTokenAgain(String refreshToken);

    UserDTO getUserByRefreshToken(String refreshToken);

    void deleteByUserId(UUID userID);
}
