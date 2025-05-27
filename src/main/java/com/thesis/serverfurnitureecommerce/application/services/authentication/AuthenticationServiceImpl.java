package com.thesis.serverfurnitureecommerce.application.services.authentication;

import com.thesis.serverfurnitureecommerce.common.constant.RoleConstant;
import com.thesis.serverfurnitureecommerce.domain.model.entity.RoleEntity;
import com.thesis.serverfurnitureecommerce.domain.repository.RoleRepository;
import com.thesis.serverfurnitureecommerce.presentation.requestv2.AuthenticationRequest;
import com.thesis.serverfurnitureecommerce.presentation.requestv2.CustomerRegisterRequest;
import com.thesis.serverfurnitureecommerce.presentation.requestv2.LogoutRequest;
import com.thesis.serverfurnitureecommerce.presentation.requestv2.RegisterRequest;
import com.thesis.serverfurnitureecommerce.presentation.response.UserInfo;
import com.thesis.serverfurnitureecommerce.domain.repository.UserRepository;
import com.thesis.serverfurnitureecommerce.domain.repository.InvalidatedTokenRepository;
import com.thesis.serverfurnitureecommerce.domain.model.entity.InvalidatedTokenEntity;
import com.thesis.serverfurnitureecommerce.domain.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.domain.exception.AppException;
import com.thesis.serverfurnitureecommerce.domain.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.common.mapper.UserMapper;
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
import java.util.Optional;

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
    private final RoleRepository roleRepository;

    @Override
    public UserEntity authenticate(AuthenticationRequest authenticationRequest) {
        log.info("Authenticating user: {}", authenticationRequest.username());
        authenticateUser(authenticationRequest);
        UserEntity userEntity = findUser(authenticationRequest.username());
        validateUserStatus(userEntity);
        return userEntity;
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {
        log.info("Logging out user with token: {}", logoutRequest.token());
        validateAuthentication();
        invalidateToken(logoutRequest.token());
        SecurityContextHolder.clearContext();
    }

    @Override
    public UserInfo signUp(CustomerRegisterRequest customerRegisterRequest) {
        log.info("Customer register account");
        if (userRepository.findByUsername(customerRegisterRequest.username()).isPresent()) {
            throw new AppException(ErrorCode.USERNAME_EXIST);
        }
        if (userRepository.findByEmail(customerRegisterRequest.email()).isPresent()) {
            throw new AppException(ErrorCode.EMAIL_EXIST);
        }
        UserEntity userEntity = userMapper.toUserEntity(customerRegisterRequest);
        userEntity.setPassword(passwordEncoder.encode(customerRegisterRequest.password()));
        userEntity.setIsActive((short) 1);
        RoleEntity role = getRoleUser();
        userEntity.setRole(role);
        userEntity.setIsLocked((short) 0);
        UserEntity user = userRepository.save(userEntity);
        if (user == null) {
            throw new AppException(ErrorCode.USER_REGISTER_FAIL);
        }
        return userMapper.toUserInfo(user);
    }

    private RoleEntity getRoleUser() {
        return roleRepository.findByName(RoleConstant.CUSTOMER)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
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
}
