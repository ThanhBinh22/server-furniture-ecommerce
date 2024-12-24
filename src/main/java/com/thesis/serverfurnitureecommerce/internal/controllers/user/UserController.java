package com.thesis.serverfurnitureecommerce.internal.controllers.user;

import com.thesis.serverfurnitureecommerce.domain.request.AccountVerifyRequest;
import com.thesis.serverfurnitureecommerce.domain.request.EmailRequest;
import com.thesis.serverfurnitureecommerce.domain.request.NewPasswordRequest;
import com.thesis.serverfurnitureecommerce.domain.request.UpdateAccountRequest;
import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.domain.response.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.services.logs.UserLogService;
import com.thesis.serverfurnitureecommerce.internal.services.user.UserService;
import com.thesis.serverfurnitureecommerce.model.dto.UserDTO;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.pkg.utils.annotation.ApiMessage;
import jakarta.servlet.http.HttpServletRequest;
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
    UserService userService;
    UserLogService userLogService;

    @PostMapping("/forgot-password")
    public ResponseEntity<APIResponse<Void>> forgotPassword(@RequestBody EmailRequest emailRequest) {
        log.info("Request to forgot password for email: {}", emailRequest.getEmail());
        return handleUserAction(() -> {
            userService.forgotPassword(emailRequest.getEmail());
            return ResponseBuilder.buildResponse(null, ErrorCode.SUCCESS);
        });
    }

    @PostMapping("/verify-forget-password")
    public ResponseEntity<APIResponse<String>> verifyForgetPassword(@RequestBody AccountVerifyRequest accountVerifyRequest) {
        log.info("Request to verify forgot password for email: {}", accountVerifyRequest.getEmail());
        return handleUserAction(() -> {
            userService.verifyForgetPassword(accountVerifyRequest);
            return ResponseBuilder.buildResponse("OTP verified successfully", ErrorCode.SUCCESS);
        });
    }

    @PostMapping("/change-password")
    public ResponseEntity<APIResponse<String>> changePassword(@RequestBody @Valid NewPasswordRequest newPasswordRequest, HttpServletRequest httpServletRequest) {
        log.info("Request to change password for email: {}", newPasswordRequest.getEmail());
        userLogService.log("Change password", "INFO", "User require change password", null, httpServletRequest.getRemoteAddr());
        userService.changePassword(newPasswordRequest);
        return ResponseBuilder.buildResponse("Password changed successfully", ErrorCode.SUCCESS);
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<APIResponse<Void>> deleteAccount(@RequestParam Long userID, HttpServletRequest httpServletRequest) {
        log.info("Request to delete account for email: {}", userID);
        return handleUserAction(() -> {
            userLogService.log("Delete account", "INFO", "User require delete account", null, httpServletRequest.getRemoteAddr());
            userService.deleteAccount(userID);
            return ResponseBuilder.buildResponse(null, ErrorCode.SUCCESS);
        });
    }

    @GetMapping("/view-profile")
    public ResponseEntity<APIResponse<UserDTO>> viewProfile(@RequestParam Long userID, HttpServletRequest httpServletRequest) {
        log.info("Request to view profile for user: {}", userID);
        return handleUserAction(() -> {
            userLogService.log("View profile", "INFO", "User require view profile", null, httpServletRequest.getRemoteAddr());
            UserDTO userDTO = userService.viewProfile(userID);
            return ResponseBuilder.buildResponse(userDTO, ErrorCode.SUCCESS);
        });
    }

    @GetMapping("/get-information-user")
    public ResponseEntity<APIResponse<UserDTO>> getInformationUser(@RequestParam String accessToken, HttpServletRequest httpServletRequest) {
        log.info("Request to get information user");
        return handleUserAction(() -> {
            userLogService.log("Get information user", "INFO", "User require get information user", null, httpServletRequest.getRemoteAddr());
            UserDTO userDTO = userService.getInformationUser(accessToken);
            return ResponseBuilder.buildResponse(userDTO, ErrorCode.SUCCESS);
        });
    }

    @PutMapping("/update-account")
    public ResponseEntity<APIResponse<UpdateAccountRequest>> updateAccount(@RequestBody UpdateAccountRequest updateAccountRequest, HttpServletRequest httpServletRequest) {
        log.info("Request to update account for user: {}", updateAccountRequest.getUsername());
        return handleUserAction(() -> {
            userLogService.log("Update account", "INFO", "User require update account", null, httpServletRequest.getRemoteAddr());
            UpdateAccountRequest accountRequest = userService.updateProfile(updateAccountRequest);
            return ResponseBuilder.buildResponse(accountRequest, ErrorCode.SUCCESS);
        });
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
