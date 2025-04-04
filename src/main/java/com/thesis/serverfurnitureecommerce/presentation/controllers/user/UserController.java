package com.thesis.serverfurnitureecommerce.presentation.controllers.user;

import com.thesis.serverfurnitureecommerce.presentation.requestv2.AccountVerifyRequest;
import com.thesis.serverfurnitureecommerce.presentation.requestv2.EmailRequest;
import com.thesis.serverfurnitureecommerce.presentation.requestv2.NewPasswordRequest;
import com.thesis.serverfurnitureecommerce.presentation.requestv2.UpdateAccountRequest;
import com.thesis.serverfurnitureecommerce.presentation.response.APIResponse;
import com.thesis.serverfurnitureecommerce.common.utils.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.presentation.controllers.BaseController;
import com.thesis.serverfurnitureecommerce.application.services.logs.UserLogService;
import com.thesis.serverfurnitureecommerce.application.services.user.UserService;
import com.thesis.serverfurnitureecommerce.domain.model.vo.UserVO;
import com.thesis.serverfurnitureecommerce.domain.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController extends BaseController {
    UserService userService;
    UserLogService userLogService;

    @PostMapping("/forgot-password")
    public ResponseEntity<APIResponse<Void>> forgotPassword(@RequestBody EmailRequest emailRequest) {
        return handleAction(() -> {
            userService.forgotPassword(emailRequest.email());
            return ResponseBuilder.buildResponse(null, ErrorCode.SUCCESS);
        });
    }

    @PostMapping("/verify-forget-password")
    public ResponseEntity<APIResponse<String>> verifyForgetPassword(@RequestBody AccountVerifyRequest accountVerifyRequest) {
        return handleAction(() -> {
            userService.verifyForgetPassword(accountVerifyRequest);
            return ResponseBuilder.buildResponse("OTP verified successfully", ErrorCode.SUCCESS);
        });
    }

    @PostMapping("/change-password")
    public ResponseEntity<APIResponse<String>> changePassword(@RequestBody @Valid NewPasswordRequest newPasswordRequest, HttpServletRequest httpServletRequest) {
        userLogService.log("Change password", "INFO", "User require change password", null, httpServletRequest.getRemoteAddr());
        userService.changePassword(newPasswordRequest);
        return ResponseBuilder.buildResponse("Password changed successfully", ErrorCode.SUCCESS);
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<APIResponse<Void>> deleteAccount(@RequestParam UUID userID, HttpServletRequest httpServletRequest) {
        return handleAction(() -> {
            userLogService.log("Delete account", "INFO", "User require delete account", null, httpServletRequest.getRemoteAddr());
            userService.deleteAccount(userID);
            return ResponseBuilder.buildResponse(null, ErrorCode.SUCCESS);
        });
    }

    @GetMapping("/view-profile")
    public ResponseEntity<APIResponse<UserVO>> viewProfile(@RequestParam UUID userID, HttpServletRequest httpServletRequest) {
        return handleAction(() -> {
            userLogService.log("View profile", "INFO", "User require view profile", null, httpServletRequest.getRemoteAddr());
            UserVO userVO = userService.viewProfile(userID);
            return ResponseBuilder.buildResponse(userVO, ErrorCode.SUCCESS);
        });
    }

    @GetMapping("/get-information-user")
    public ResponseEntity<APIResponse<UserVO>> getInformationUser(@RequestParam String accessToken, HttpServletRequest httpServletRequest) {
        return handleAction(() -> {
            userLogService.log("Get information user", "INFO", "User require get information user", null, httpServletRequest.getRemoteAddr());
            UserVO userVO = userService.getInformationUser(accessToken);
            return ResponseBuilder.buildResponse(userVO, ErrorCode.SUCCESS);
        });
    }

    @PutMapping("/update-account")
    public ResponseEntity<APIResponse<UpdateAccountRequest>> updateAccount(@RequestBody UpdateAccountRequest updateAccountRequest, HttpServletRequest httpServletRequest) {
        return handleAction(() -> {
            userLogService.log("Update account", "INFO", "User require update account", null, httpServletRequest.getRemoteAddr());
            UpdateAccountRequest accountRequest = userService.updateProfile(updateAccountRequest);
            return ResponseBuilder.buildResponse(accountRequest, ErrorCode.SUCCESS);
        });
    }
}
