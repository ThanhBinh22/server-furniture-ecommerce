package com.thesis.serverfurnitureecommerce.internal.services.authentication;

import com.thesis.serverfurnitureecommerce.domain.request.AuthenticationRequest;
import com.thesis.serverfurnitureecommerce.domain.request.LogoutRequest;
import com.thesis.serverfurnitureecommerce.internal.repositories.IUserRepository;
import com.thesis.serverfurnitureecommerce.internal.repositories.InvalidatedTokenRepository;
import com.thesis.serverfurnitureecommerce.model.entity.InvalidatedTokenEntity;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
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
public class AuthenticationService implements IAuthenticationService {
    IUserRepository userRepository;
    AuthenticationManager authenticationManager;
    PasswordEncoder passwordEncoder;
    InvalidatedTokenRepository invalidatedTokenRepository;

    @Override
    public UserEntity authenticate(AuthenticationRequest authenticationRequest) {
        log.info("Service is solving authentication with account: {}", authenticationRequest.getUsername());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        UserEntity userEntity = userRepository.findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (!userEntity.isEnabled()) {
            throw new AppException(ErrorCode.USER_NOT_ENABLED);
        }
        if (!userEntity.isAccountNonLocked()) {
            throw new AppException(ErrorCode.USER_LOCKED);
        }

        return userEntity;
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {
        log.info("Service is solving logout with account has token: {}", logoutRequest.getToken());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AppException(ErrorCode.USER_NOT_AUTHENTICATE);
        }
        String token = logoutRequest.getToken();
        InvalidatedTokenEntity invalidatedToken = new InvalidatedTokenEntity();
        invalidatedToken.setTokenId(token);
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(30);
        invalidatedToken.setExpired(expirationTime);
        invalidatedTokenRepository.save(invalidatedToken);
        SecurityContextHolder.clearContext();
    }
}
