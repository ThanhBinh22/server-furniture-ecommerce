package com.thesis.serverfurnitureecommerce.internal.services.user;

import com.thesis.serverfurnitureecommerce.domain.request.AccountVerifyRequest;
import com.thesis.serverfurnitureecommerce.domain.request.NewPasswordRequest;
import com.thesis.serverfurnitureecommerce.internal.repositories.IUserRepository;
import com.thesis.serverfurnitureecommerce.internal.services.email.IEmailService;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.pkg.utils.OtpGenerator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements IUserService {
    IUserRepository userRepository;
    IEmailService emailService;
    PasswordEncoder passwordEncoder;

    @Override
    public void forgotPassword(String email) {
        log.info("Sending OTP to email: {}", email);
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_EMAIL));
        int otp = OtpGenerator.generate6DigitOtp();
        log.info("Generated OTP: {}", otp);
        user.setOtp(otp);
        user.setOtpExpired(LocalDateTime.now().plus(Duration.ofMinutes(3)));
        userRepository.save(user);
        emailService.sendMailForgotPassword(email, otp);
    }

    @Override
    public void verifyForgetPassword(AccountVerifyRequest accountVerifyRequest) {
        log.info("Verifying OTP for email: {}", accountVerifyRequest.getEmail());
        UserEntity user = userRepository.findByEmail(accountVerifyRequest.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        checkOtpExpiration(user);
        if (isOtpValid(accountVerifyRequest.getOtp(), user.getOtp())) {
            clearOtp(user);
        } else {
            throw new AppException(ErrorCode.INVALID_OTP);
        }
    }

    private void checkOtpExpiration(UserEntity user) {
        if (user.getOtpExpired() != null && user.getOtpExpired().isBefore(LocalDateTime.now())) {
            throw new AppException(ErrorCode.OTP_EXPIRED);
        }
    }

    private boolean isOtpValid(String inputOtp, Integer userOtp) {
        return inputOtp.equals(String.valueOf(userOtp));
    }

    private void clearOtp(UserEntity user) {
        user.setOtp(null);
        user.setOtpExpired(null);
        userRepository.save(user);
    }

    @Override
    public void changePassword(NewPasswordRequest newPasswordRequest) {
        log.info("Changing password for email: {}", newPasswordRequest.getEmail());
        UserEntity user = userRepository.findByEmail(newPasswordRequest.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        user.setPassword(passwordEncoder.encode(newPasswordRequest.getPassword()));
        userRepository.save(user);
    }
}
