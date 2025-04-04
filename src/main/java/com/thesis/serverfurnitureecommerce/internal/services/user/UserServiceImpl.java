package com.thesis.serverfurnitureecommerce.internal.services.user;

import com.thesis.serverfurnitureecommerce.domain.requestv2.AccountVerifyRequest;
import com.thesis.serverfurnitureecommerce.domain.requestv2.NewPasswordRequest;
import com.thesis.serverfurnitureecommerce.domain.requestv2.UpdateAccountRequest;
import com.thesis.serverfurnitureecommerce.internal.repositories.UserRepository;
import com.thesis.serverfurnitureecommerce.internal.services.email.EmailService;
import com.thesis.serverfurnitureecommerce.model.dto.UserDTO;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.pkg.mapper.UserMapper;
import com.thesis.serverfurnitureecommerce.pkg.utils.OtpGenerator;
import com.thesis.serverfurnitureecommerce.pkg.utils.UserUtil;
import io.jsonwebtoken.Jwts;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;
    final EmailService emailService;
    final PasswordEncoder passwordEncoder;
    final UserMapper userMapper;

    @Value("${security.jwt.secret-key}")
    String secretKey;

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
        log.info("Verifying OTP for email: {}", accountVerifyRequest.email());
        UserEntity user = userRepository.findByEmail(accountVerifyRequest.email())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        checkOtpExpiration(user);
        if (isOtpValid(accountVerifyRequest.otp(), user.getOtp())) {
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
        log.info("Changing password for email: {}", newPasswordRequest.email());
        UserEntity user = userRepository.findByEmail(newPasswordRequest.email())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        user.setPassword(passwordEncoder.encode(newPasswordRequest.password()));
        userRepository.save(user);
    }

    @Override
    public void deleteAccount(UUID userID) {
        log.info("Deleting account for user ID: {}", userID);
        UserEntity user = userRepository.findById(userID)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        try {
            userRepository.delete(user);
        } catch (Exception ex) {
            throw new AppException(ErrorCode.DELETE_FAILED);
        }

    }

    @Override
    public UpdateAccountRequest updateProfile(UpdateAccountRequest updateAccountRequest) {
        String username = UserUtil.getUsername();
        log.info("Updating profile for username: {}", username);
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        BeanUtils.copyProperties(updateAccountRequest, user, getNullPropertyNames(updateAccountRequest));
        userRepository.save(user);
        return updateAccountRequest;
    }

    @Override
    public UserDTO viewProfile(UUID userID) {
        log.info("Viewing profile for user ID: {}", userID);
        UserEntity user = userRepository.findById(userID)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO getInformationUser(String accessToken) {
        log.info("Getting information user with access token: {}", accessToken);
        String username = extractUserIdFromToken(accessToken);
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDTO(user);
    }

    @Override
    public void blockUser(UUID userID) {
        UserEntity user = userRepository.findById(userID)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (user.getIsLocked() == 1) {
            log.info("Unlocking user ID: {}", userID);
            user.setIsLocked((short) 0);
        } else {
            log.info("Locking user ID: {}", userID);
            user.setIsLocked((short) 1);
        }
        userRepository.save(user);
    }


    @Override
    public List<UserDTO> getAllUser() {
        List<UserEntity> users = userRepository.findAll();
        if (!users.isEmpty()) {
            return userMapper.toListDTO(users);
        }
        return List.of();
    }

    private String extractUserIdFromToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        Set<String> emptyNames = new HashSet<>();

        for (var propertyDescriptor : wrappedSource.getPropertyDescriptors()) {
            String propertyName = propertyDescriptor.getName();
            if (wrappedSource.getPropertyValue(propertyName) == null) {
                emptyNames.add(propertyName);
            }
        }
        return emptyNames.toArray(new String[0]);
    }
}
