package com.thesis.serverfurnitureecommerce.application.services.account;

import com.thesis.serverfurnitureecommerce.common.constant.RoleConstant;
import com.thesis.serverfurnitureecommerce.presentation.requestv2.RegisterRequest;
import com.thesis.serverfurnitureecommerce.infrastructure.persistence.RoleRepository;
import com.thesis.serverfurnitureecommerce.infrastructure.persistence.UserRepository;
import com.thesis.serverfurnitureecommerce.application.services.email.EmailService;
import com.thesis.serverfurnitureecommerce.domain.model.entity.RoleEntity;
import com.thesis.serverfurnitureecommerce.domain.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.domain.exception.AppException;
import com.thesis.serverfurnitureecommerce.domain.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.common.mapper.UserMapper;
import com.thesis.serverfurnitureecommerce.common.utils.OtpGenerator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {
    UserRepository userRepository;
    EmailService emailService;
    UserMapper userMapper;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;


    @Override
    public void registerAccount(RegisterRequest registerRequest) {
        UserEntity userByUsername = findUserByUsername(registerRequest.username());
        if (userByUsername != null && userByUsername.getIsActive() == 1) {
            log.warn("User already exists with username: {}", registerRequest.username());
            throw new AppException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }
        if (userByUsername != null && userByUsername.getIsActive() == 0) {
            handleExistingUser(userByUsername, registerRequest.password());
            return;
        }
        UserEntity userByEmail = findUserByEmail(registerRequest.email());
        if (userByEmail != null && userByEmail.getIsActive() == 1) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
        if (userByEmail != null && userByEmail.getIsActive() == 0) {
            handleExistingUser(userByEmail, registerRequest.password());

            return;
        }
        createNewUser(registerRequest);
    }

    private UserEntity findUserByUsername(String username) {
        log.info("Invoke to service find user by username");
        return userRepository.findByUsername(username)
                .orElse(null);
    }

    private UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .filter(user -> user.getIsActive() == 0)
                .orElse(null);
    }


    private void handleExistingUser(UserEntity user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        user.setOtp(OtpGenerator.generate6DigitOtp());
        user.setOtpExpired(LocalDateTime.now().plus(Duration.ofMinutes(3)));
        userRepository.save(user);
        log.info("Updated existing user: {}", user.getEmail());
        emailService.sendMailOTP(user.getEmail(), user.getOtp());
    }

    private void createNewUser(RegisterRequest registerRequest) {
        UserEntity userEntity = userMapper.fromRequestToEntity(registerRequest);
        userEntity.setPassword(passwordEncoder.encode(registerRequest.password()));
        userEntity.setOtp(OtpGenerator.generate6DigitOtp());
        userEntity.setIsActive((short) 0);
        userEntity.setOtpExpired(LocalDateTime.now().plus(Duration.ofMinutes(3)));
        RoleEntity role = roleRepository.findByName(RoleConstant.USER)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        userEntity.setRole(role);
        userRepository.save(userEntity);
        emailService.sendMailOTP(userEntity.getEmail(), userEntity.getOtp());
    }

    @Override
    public Boolean verifyAccountAfterRegister(String otp) {
        UserEntity user = userRepository.findByOtp(Integer.parseInt(otp))
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        // Kiểm tra nếu OTP đã hết hạn
        if (user.getOtpExpired().isBefore(LocalDateTime.now())) {
            throw new AppException(ErrorCode.OTP_EXPIRED);
        }
        // Kiểm tra nếu OTP hợp lệ
        if (otp.equals(user.getOtp().toString())) {
            activateUser(user);
            return true;
        }
        return false;
    }

    private void activateUser(UserEntity user) {
        user.setIsActive((short) 1);
        user.setOtp(null);
        user.setOtpExpired(null);
        userRepository.save(user);
    }

    @Override
    public void resendOTP(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        user.setOtp(OtpGenerator.generate6DigitOtp());
        user.setOtpExpired(LocalDateTime.now().plus(Duration.ofMinutes(3)));
        emailService.sendMailOTP(user.getEmail(), user.getOtp()); // Gửi lại OTP
        userRepository.save(user);
    }

    @Override
    public boolean isAccountVerified(String email) {
        return userRepository.findByEmail(email)
                .map(user -> user.getIsActive() == 1)
                .orElse(false);
    }

}
