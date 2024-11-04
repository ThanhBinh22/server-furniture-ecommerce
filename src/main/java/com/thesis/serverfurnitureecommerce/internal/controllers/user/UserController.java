package com.thesis.serverfurnitureecommerce.internal.controllers.user;

import com.thesis.serverfurnitureecommerce.domain.request.AccountVerifyRequest;
import com.thesis.serverfurnitureecommerce.domain.request.EmailRequest;
import com.thesis.serverfurnitureecommerce.domain.request.NewPasswordRequest;
import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.domain.response.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.services.user.IUserService;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.pkg.utils.annotation.ApiMessage;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.InternalApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    IUserService userService;

    @ApiMessage("Forgot password")
    @PostMapping("/forgot-password")
    public ResponseEntity<APIResponse<Void>> forgotPassword(@RequestBody EmailRequest emailRequest) {
        log.info("Request to forgot password for email: {}", emailRequest.getEmail());
        return handleUserAction(() -> {
            userService.forgotPassword(emailRequest.getEmail());
            return ResponseBuilder.buildResponse(null, ErrorCode.SUCCESS);
        });
    }

    @ApiMessage("Verify forgot password")
    @PostMapping("/verify-forget-password")
    public ResponseEntity<APIResponse<String>> verifyForgetPassword(@RequestBody AccountVerifyRequest accountVerifyRequest) {
        log.info("Request to verify forgot password for email: {}", accountVerifyRequest.getEmail());
        return handleUserAction(() -> {
            userService.verifyForgetPassword(accountVerifyRequest);
            return ResponseBuilder.buildResponse("OTP verified successfully", ErrorCode.SUCCESS);
        });
    }

    @ApiMessage("Change password")
    @PostMapping("/change-password")
    public ResponseEntity<APIResponse<String>> changePassword(@RequestBody @Valid NewPasswordRequest newPasswordRequest) {
        log.info("Request to change password for email: {}", newPasswordRequest.getEmail());
        userService.changePassword(newPasswordRequest);
        return ResponseBuilder.buildResponse("Password changed successfully", ErrorCode.SUCCESS);
    }

    private <T> ResponseEntity<APIResponse<T>> handleUserAction(UserAction<T> action) {
        try {
            return action.execute();
        } catch (AppException ex) {
            log.error("Error during user action: {}", ex.getMessage());
            return ResponseBuilder.buildResponse(null, ex.getErrorCode());
        }
    }

    @FunctionalInterface
    private interface UserAction<T> {
        ResponseEntity<APIResponse<T>> execute();
    }
}
