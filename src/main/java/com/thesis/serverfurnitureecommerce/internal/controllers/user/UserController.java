package com.thesis.serverfurnitureecommerce.internal.controllers.user;

import com.thesis.serverfurnitureecommerce.domain.request.AccountVerifyRequest;
import com.thesis.serverfurnitureecommerce.domain.request.RegisterRequest;
import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.domain.response.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.services.account.IAccountService;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    IAccountService accountService;

    @PostMapping("/sign-up")
    public ResponseEntity<APIResponse<RegisterRequest>> register(@RequestBody RegisterRequest registerRequest) {
        log.info("register");
        RegisterRequest result = accountService.RegisterAccount(registerRequest);
        return ResponseBuilder.buildResponse(result, result != null ? ErrorCode.CREATE_SUCCESS : ErrorCode.CREATE_FAILED);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<APIResponse<Void>> verifyOtp(@RequestBody @Valid AccountVerifyRequest accountVerifyRequest) {
        log.info("OTP verification request for email: {}", accountVerifyRequest.getEmail());
        try {
            accountService.verifyAccountAfterRegister(accountVerifyRequest);
            return ResponseBuilder.buildResponse(null, ErrorCode.SUCCESS);
        } catch (AppException ex) {
            log.error("OTP verification failed for email: {}", accountVerifyRequest.getEmail(), ex);
            return ResponseBuilder.buildResponse(null, ex.getErrorCode());
        }
    }


}
