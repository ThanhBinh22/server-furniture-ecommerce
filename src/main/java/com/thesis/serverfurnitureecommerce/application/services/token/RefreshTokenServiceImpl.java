package com.thesis.serverfurnitureecommerce.application.services.token;

import com.thesis.serverfurnitureecommerce.infrastructure.persistence.RefreshTokenRepository;
import com.thesis.serverfurnitureecommerce.infrastructure.persistence.UserRepository;
import com.thesis.serverfurnitureecommerce.application.services.jwt.JwtService;
import com.thesis.serverfurnitureecommerce.domain.model.vo.UserVO;
import com.thesis.serverfurnitureecommerce.domain.model.entity.RefreshTokenEntity;
import com.thesis.serverfurnitureecommerce.domain.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.domain.exception.AppException;
import com.thesis.serverfurnitureecommerce.domain.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.common.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RefreshTokenServiceImpl implements RefreshTokenService {
    RefreshTokenRepository refreshTokenRepository;
    UserRepository userRepository;
    JwtService jwtService;
    UserMapper userMapper;

    @Override
    public RefreshTokenEntity createRefreshToken(UserEntity user) {
        RefreshTokenEntity refreshToken = RefreshTokenEntity.createRefreshToken();
        refreshToken.setUser(user);
        refreshToken.setTokenId(UUID.randomUUID().toString());
        refreshToken.setExpired(LocalDateTime.now().plusDays(7));
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshTokenEntity verifyExpiration(RefreshTokenEntity token) {
        if (token.getExpired().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(token);
            throw new AppException(ErrorCode.TOKEN_EXPIRED);
        }
        return token;
    }

    @Override
    public String generateAccessTokenAgain(String refreshToken) {
        return refreshTokenRepository.findByTokenId(refreshToken)
                .map(this::verifyExpiration)
                .map(RefreshTokenEntity::getUser)
                .map(jwtService::generateToken)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_TOKEN));
    }

    @Override
    public UserVO getUserByRefreshToken(String refreshToken) {
        RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findByTokenId(refreshToken)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_TOKEN));
        UserEntity user = userRepository.findById(refreshTokenEntity.getUser().getId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toDTO(user);
    }

    @Override
    public void deleteByUserId(UUID userID) {
        userRepository.deleteById(userID);
    }



}
