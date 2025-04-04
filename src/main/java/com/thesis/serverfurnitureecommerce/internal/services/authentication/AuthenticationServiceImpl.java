package com.thesis.serverfurnitureecommerce.internal.services.authentication;

import com.thesis.serverfurnitureecommerce.domain.requestv2.AuthenticationRequest;
import com.thesis.serverfurnitureecommerce.domain.requestv2.LogoutRequest;
import com.thesis.serverfurnitureecommerce.internal.repositories.UserRepository;
import com.thesis.serverfurnitureecommerce.internal.repositories.InvalidatedTokenRepository;
import com.thesis.serverfurnitureecommerce.model.entity.InvalidatedTokenEntity;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.pkg.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepository;
    AuthenticationManager authenticationManager;
    PasswordEncoder passwordEncoder;
    InvalidatedTokenRepository invalidatedTokenRepository;
    UserMapper userMapper;

    @Override
    public UserEntity authenticate(AuthenticationRequest authenticationRequest) {
        log.info("Authenticating user: {}", authenticationRequest.username());
        authenticateUser(authenticationRequest);
        UserEntity userEntity = findUser(authenticationRequest.username());
        validateUserStatus(userEntity);
        return userEntity;
    }

    private void authenticateUser(AuthenticationRequest authenticationRequest) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.username(),
                authenticationRequest.password()
        );
        authenticationManager.authenticate(authenticationToken);
    }

    private UserEntity findUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    private void validateUserStatus(UserEntity userEntity) {
        if (!userEntity.isEnabled()) {
            throw new AppException(ErrorCode.USER_NOT_ENABLED);
        }
        if (!userEntity.isAccountNonLocked()) {
            throw new AppException(ErrorCode.USER_LOCKED);
        }
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {
        log.info("Logging out user with token: {}", logoutRequest.token());
        validateAuthentication();
        invalidateToken(logoutRequest.token());
        SecurityContextHolder.clearContext();
    }

    private void validateAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AppException(ErrorCode.USER_NOT_AUTHENTICATE);
        }
    }

    private void invalidateToken(String token) {
        InvalidatedTokenEntity invalidatedToken = createInvalidatedToken(token);
        invalidatedTokenRepository.save(invalidatedToken);
    }

    private InvalidatedTokenEntity createInvalidatedToken(String token) {
        InvalidatedTokenEntity invalidatedToken = InvalidatedTokenEntity.createInvalidatedToken();
        invalidatedToken.setTokenId(token);
        invalidatedToken.setExpired(LocalDateTime.now().plusMinutes(30));
        return invalidatedToken;
    }
}
