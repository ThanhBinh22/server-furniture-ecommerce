package com.thesis.serverfurnitureecommerce.application.service;

import com.thesis.serverfurnitureecommerce.application.services.email.EmailService;
import com.thesis.serverfurnitureecommerce.application.services.user.UserServiceImpl;
import com.thesis.serverfurnitureecommerce.domain.exception.AppException;
import com.thesis.serverfurnitureecommerce.domain.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.domain.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.domain.repository.UserRepository;
import com.thesis.serverfurnitureecommerce.presentation.requestv2.AccountVerifyRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    UserRepository userRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void testForgotPassword_WhenEmailIsValid_ShouldSendOtpAndSaveUser() {
        String email = "thanhbinh2757@gmail.com";
        UserEntity user = new UserEntity();
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        userService.forgotPassword(email);

        assertNotNull(user.getOtp());
        assertNotNull(user.getOtpExpired());
        verify(userRepository).save(user);
        verify(emailService).sendMailForgotPassword(eq(email), anyInt());
    }

    @Test
    void testForgotPassword_WhenEmailIsInvalid_ShouldThrowException() {
        String email = "notfound@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        AppException exception = assertThrows(AppException.class, () -> {
            userService.forgotPassword(email);
        });

        assertEquals(ErrorCode.INVALID_EMAIL, exception.getErrorCode());

        verify(userRepository, never()).save(any());
        verify(emailService, never()).sendMailForgotPassword(anyString(), anyInt());
    }

    @Test
    void verifyForgetPassword_validOtp_shouldClearOtpAndSaveUser() {
        String email = "binnguci@example.com";
        AccountVerifyRequest request = new AccountVerifyRequest(email, "123456");

        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setOtp(123456);
        user.setOtpExpired(LocalDateTime.now().plusMinutes(5));

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        userService.verifyForgetPassword(request);

        assertNull(user.getOtp());
        assertNull(user.getOtpExpired());
        verify(userRepository).save(user);
    }

    @Test
    void verifyForgetPassword_invalidOtp_shouldThrowException() {
        String email = "binnguci@example.com";
        AccountVerifyRequest request = new AccountVerifyRequest(email, "000000");

        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setOtp(123456);
        user.setOtpExpired(LocalDateTime.now().plusMinutes(5));

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        AppException ex = assertThrows(AppException.class, () -> userService.verifyForgetPassword(request));
        assertEquals(ErrorCode.INVALID_OTP, ex.getErrorCode());
    }

    @Test
    void verifyForgetPassword_expiredOtp_shouldThrowException() {
        String email = "binnguci@example.com";
        AccountVerifyRequest request = new AccountVerifyRequest(email, "123456");

        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setOtp(123456);
        user.setOtpExpired(LocalDateTime.now().minusMinutes(1)); // hết hạn

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        AppException ex = assertThrows(AppException.class, () -> userService.verifyForgetPassword(request));
        assertEquals(ErrorCode.OTP_EXPIRED, ex.getErrorCode());
    }

    @Test
    void verifyForgetPassword_userNotFound_shouldThrowException() {
        String email = "notfound@example.com";
        AccountVerifyRequest request = new AccountVerifyRequest(email, "123456");

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        AppException ex = assertThrows(AppException.class, () -> userService.verifyForgetPassword(request));
        assertEquals(ErrorCode.USER_NOT_FOUND, ex.getErrorCode());
    }

    @Test
    void verifyForgetPassword_validUser_shouldClearOtpAndSaveUser() {

    }

}
