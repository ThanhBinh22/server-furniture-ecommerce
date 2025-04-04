package com.thesis.serverfurnitureecommerce.application.services.token;

import com.thesis.serverfurnitureecommerce.domain.model.vo.UserVO;
import com.thesis.serverfurnitureecommerce.domain.model.entity.RefreshTokenEntity;
import com.thesis.serverfurnitureecommerce.domain.model.entity.UserEntity;

import java.util.UUID;

public interface RefreshTokenService {
    RefreshTokenEntity createRefreshToken(UserEntity user);

    RefreshTokenEntity verifyExpiration(RefreshTokenEntity token);

    String generateAccessTokenAgain(String refreshToken);

    UserVO getUserByRefreshToken(String refreshToken);

    void deleteByUserId(UUID userID);
}
