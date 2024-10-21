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
        Optional<UserEntity> existingUserByUsername = userRepository.findByUsername(registerRequest.getUsername());
        if (existingUserByUsername.isPresent()) {
            UserEntity userByUsername = existingUserByUsername.get();
            if (userByUsername.getIsActive() == 0) {
                userByUsername.setPassword(passwordEncoder.encode(registerRequest.getPassword())); // Cập nhật mật khẩu
                userByUsername.setOtp(OtpGenerator.generate6DigitOtp()); // Tạo mã OTP mới
                userByUsername.setOtpExpired(LocalDateTime.now().plus(Duration.ofMinutes(3))); // Thời gian hết hạn OTP
                userRepository.save(userByUsername);
                log.info("Updated existing user with username: {}", registerRequest.getUsername());
                emailService.sendMailOTP(userByUsername.getEmail(), userByUsername.getOtp()); // Gửi email OTP
                return;
            } else {
                throw new AppException(ErrorCode.USERNAME_ALREADY_EXISTS);
            }
        }
        Optional<UserEntity> existingUserByEmail = userRepository.findByEmail(registerRequest.getEmail());
        if (existingUserByEmail.isPresent()) {
            UserEntity userByUserEmail = existingUserByEmail.get();
            if (userByUserEmail.getIsActive() == 0) {
                userByUserEmail.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
                userByUserEmail.setOtp(OtpGenerator.generate6DigitOtp());
                userByUserEmail.setOtpExpired(LocalDateTime.now().plus(Duration.ofMinutes(3)));

                userRepository.save(userByUserEmail);

                log.info("Updated existing user with email: {}", registerRequest.getEmail());
                emailService.sendMailOTP(userByUserEmail.getEmail(), userByUserEmail.getOtp()); // Gửi email OTP
                return;
            }
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
        int otp = OtpGenerator.generate6DigitOtp();
        UserEntity userEntity = userMapper.fromRequestToEntity(registerRequest);
        userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userEntity.setOtp(otp);
        userEntity.setIsActive((short) 0);
        userEntity.setOtpExpired(LocalDateTime.now().plus(Duration.ofMinutes(3)));
        RoleEntity role = roleRepository.findByName("USER").orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        userEntity.setRole(role);
        userRepository.save(userEntity);
        log.info("Invoke function send mail");
        emailService.sendMailOTP(userEntity.getEmail(), otp);
    }


    @Override
    public Boolean verifyAccountAfterRegister(String otp) {
        Optional<UserEntity> userFindByOtp = userRepository.findByOtp(Integer.parseInt(otp));
        if (userFindByOtp.isPresent()) {
            UserEntity user = userFindByOtp.get();
            if (user.getOtpExpired().isBefore(LocalDateTime.now())) {
                throw new AppException(ErrorCode.OTP_EXPIRED);
            }
            if (otp.equals(user.getOtp().toString())) {
                user.setIsActive((short) 1);
                user.setOtp(null);
                user.setOtpExpired(null);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public void resendOTP(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        int otp = OtpGenerator.generate6DigitOtp();
        user.setOtp(otp);
        user.setOtpExpired(LocalDateTime.now().plus(Duration.ofMinutes(3)));
        emailService.sendMailOTP(user.getEmail(), otp);
        userRepository.save(user);
    }

}
