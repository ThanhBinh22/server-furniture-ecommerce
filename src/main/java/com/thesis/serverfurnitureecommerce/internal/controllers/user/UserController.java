package com.thesis.serverfurnitureecommerce.internal.controllers.user;

import com.thesis.serverfurnitureecommerce.domain.request.AccountVerifyRequest;
import com.thesis.serverfurnitureecommerce.domain.request.EmailRequest;
import com.thesis.serverfurnitureecommerce.domain.request.NewPasswordRequest;
import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.domain.response.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.services.user.IUserService;
import com.thesis.serverfurnitureecommerce.model.dto.UserDTO;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    IUserService userService;

    @PostMapping("/forgot-password")
    public ResponseEntity<APIResponse<Void>> forgotPassword(@RequestBody EmailRequest emailRequest) {
        log.info("Request to forgot password with email: {}", emailRequest.getEmail());
        try{
            userService.forgotPassword(emailRequest.getEmail());
            return ResponseBuilder.buildResponse(null, ErrorCode.SUCCESS);
        } catch (AppException ex) {
            log.error("Error during forgot password: {}", ex.getMessage());
            return ResponseBuilder.buildResponse(null, ex.getErrorCode());
        }
    }

    @PostMapping("/verify-forget-password")
    public ResponseEntity<APIResponse<String>> verifyForgetPassword(@RequestBody AccountVerifyRequest accountVerifyRequest) {
        log.info("Request to verify forget password with email: {}", accountVerifyRequest.getEmail());
        boolean otpTrue;
        try {
            otpTrue = userService.verifyForgetPassword(accountVerifyRequest);
            return ResponseBuilder.buildResponse("OTP verified successfully", ErrorCode.SUCCESS);
        } catch (AppException ex) {
            log.error("Error during OTP verification: {}", ex.getMessage());
            return ResponseBuilder.buildResponse(null, ex.getErrorCode());
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<APIResponse<String>> changePassword(@RequestBody @Valid NewPasswordRequest newPasswordRequest) {
        log.info("Request to change password with email: {}", newPasswordRequest.getEmail());
        userService.changePassword(newPasswordRequest);
        return ResponseBuilder.buildResponse("Password changed successfully", ErrorCode.SUCCESS);
    }
}
