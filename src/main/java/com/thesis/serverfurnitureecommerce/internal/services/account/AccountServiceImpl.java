package com.thesis.serverfurnitureecommerce.internal.services.account;

import com.thesis.serverfurnitureecommerce.domain.request.AccountVerifyRequest;
import com.thesis.serverfurnitureecommerce.domain.request.RegisterRequest;
import com.thesis.serverfurnitureecommerce.internal.repositories.IRoleRepository;
import com.thesis.serverfurnitureecommerce.internal.repositories.IUserRepository;
import com.thesis.serverfurnitureecommerce.internal.services.email.IEmailService;
import com.thesis.serverfurnitureecommerce.model.entity.RoleEntity;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.pkg.mapper.UserMapper;
import com.thesis.serverfurnitureecommerce.pkg.utils.Random;
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
    UserMapper userMapper;
    IRoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public RegisterRequest RegisterAccount(RegisterRequest registerRequest) {
        log.info("Service RegisterAccount");
        Optional<UserEntity> userFindByUsername = userRepository.findByUsername(registerRequest.getUsername());
        if (userFindByUsername.isPresent()) {
            throw new AppException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }
        Optional<UserEntity> userFindByEmail = userRepository.findByEmail(registerRequest.getEmail());
        if (userFindByEmail.isPresent()) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
        } else {
            UserEntity user = userMapper.toRequestToEntity(registerRequest);
            int otp = Random.generate6DigitOtp();
            user.setIsActive((short) 0);
            user.setOtp(otp);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setOtpExpired(LocalDateTime.now().plus(Duration.ofMinutes(3)));
            RoleEntity role = roleRepository.findByName("USER").orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
            user.setRole(role);
            emailService.sendMailOTP(user.getEmail(), otp);
            userRepository.save(user);
            return registerRequest;
        }
    }

    @Override
    public Boolean verifyAccountAfterRegister(AccountVerifyRequest accountVerifyRequest) {
        Optional<UserEntity> userFindByEmail = userRepository.findByEmail(accountVerifyRequest.getEmail());

        if (userFindByEmail.isPresent()) {
            UserEntity user = userFindByEmail.get();
            String userOTP = user.getOtp() + "";
            if ((accountVerifyRequest.getOtp().equals(userOTP)) && user.getOtpExpired().isAfter(LocalDateTime.now())) {
                user.setOtpExpired(null);
                user.setOtp(null);
                user.setIsActive((short) 1);
                userRepository.save(user);
                return true;
            } else {
                if (!user.getOtpExpired().isAfter(LocalDateTime.now())) {
                    throw new AppException(ErrorCode.OTP_EXPIRED);
                }
                if (!accountVerifyRequest.getOtp().equals(user.getOtp())) {
                    throw new AppException(ErrorCode.INVALID_OTP);
                }
            }
        } else {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        return false;
    }

    @Override
    public void resendOTP(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        int otp = Random.generate6DigitOtp();
        user.setOtp(otp);
        user.setOtpExpired(LocalDateTime.now().plus(Duration.ofMinutes(3)));
        emailService.sendMailOTP(user.getEmail(), otp);
        userRepository.save(user);
    }

}
