package com.thesis.serverfurnitureecommerce.internal.services.account;

import com.thesis.serverfurnitureecommerce.domain.request.RegisterRequest;
import com.thesis.serverfurnitureecommerce.internal.repositories.IRoleRepository;
import com.thesis.serverfurnitureecommerce.internal.repositories.IUserRepository;
import com.thesis.serverfurnitureecommerce.internal.services.email.IEmailService;
import com.thesis.serverfurnitureecommerce.model.entity.RoleEntity;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.pkg.mapper.IUserMapper;
import com.thesis.serverfurnitureecommerce.pkg.utils.OtpGenerator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements IAccountService {
    IUserRepository userRepository;
    IEmailService emailService;
    IUserMapper userMapper;
    IRoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public void RegisterAccount(RegisterRequest registerRequest) {
        log.info("Invoke to service register");
        UserEntity userByUsername = findUserByUsername(registerRequest.getUsername());
        if (userByUsername != null) {
            handleExistingUser(userByUsername, registerRequest.getPassword());
            return;
        }
        UserEntity userByEmail = findUserByEmail(registerRequest.getEmail());
        if (userByEmail != null) {
            handleExistingUser(userByEmail, registerRequest.getPassword());
            return;
        }

        createNewUser(registerRequest);
    }

    private UserEntity findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .filter(user -> user.getIsActive() == 0)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_ALREADY_EXISTS));
    }
    private UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .filter(user -> user.getIsActive() == 0)
                .orElseThrow(() -> new AppException(ErrorCode.EMAIL_ALREADY_EXISTS));
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
        userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userEntity.setOtp(OtpGenerator.generate6DigitOtp());
        userEntity.setIsActive((short) 0);
        userEntity.setOtpExpired(LocalDateTime.now().plus(Duration.ofMinutes(3)));
        RoleEntity role = roleRepository.findByName("USER")
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        userEntity.setRole(role);
        userRepository.save(userEntity);
        log.info("Invoke function send mail for new user");
        emailService.sendMailOTP(userEntity.getEmail(), userEntity.getOtp());
    }

    @Override
    public Boolean verifyAccountAfterRegister(String otp) {
        UserEntity user = userRepository.findByOtp(Integer.parseInt(otp))
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (user.getOtpExpired().isBefore(LocalDateTime.now())) {
            throw new AppException(ErrorCode.OTP_EXPIRED);
        }
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
        emailService.sendMailOTP(user.getEmail(), user.getOtp());
        userRepository.save(user);
    }
}
