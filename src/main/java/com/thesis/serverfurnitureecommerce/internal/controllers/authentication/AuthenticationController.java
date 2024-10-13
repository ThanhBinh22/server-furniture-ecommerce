package com.thesis.serverfurnitureecommerce.internal.controllers.authentication;

import com.thesis.serverfurnitureecommerce.domain.request.AccountVerifyRequest;
import com.thesis.serverfurnitureecommerce.domain.request.AuthenticationRequest;
import com.thesis.serverfurnitureecommerce.domain.request.LogoutRequest;
import com.thesis.serverfurnitureecommerce.domain.request.RegisterRequest;
import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.domain.response.LoginResponse;
import com.thesis.serverfurnitureecommerce.domain.response.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.services.account.IAccountService;
import com.thesis.serverfurnitureecommerce.internal.services.authentication.IAuthenticationService;
import com.thesis.serverfurnitureecommerce.internal.services.jwt.JwtService;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationController {
    IAccountService accountService;

    JwtService jwtService;

    IAuthenticationService authenticationService;


    @DeleteMapping("/sign-up")
    public ResponseEntity<APIResponse<RegisterRequest>> register(@RequestBody @Valid RegisterRequest registerRequest) {
        log.info("register");
        RegisterRequest result = accountService.RegisterAccount(registerRequest);
        return ResponseBuilder.buildResponse(result, result != null ? ErrorCode.CREATE_SUCCESS : ErrorCode.CREATE_FAILED);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<LoginResponse>> authenticate(@RequestBody AuthenticationRequest login) {
        UserEntity authenticatedUser = authenticationService.authenticate(login);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseBuilder.buildResponse(loginResponse, loginResponse != null ? ErrorCode.SUCCESS : ErrorCode.UNAUTHORIZED);
    }

    @PostMapping("/logout")
    public ResponseEntity<APIResponse<Void>> logout(@RequestBody @Valid LogoutRequest logoutRequest) {
        log.info("Request logout with account has token: {}", logoutRequest.getToken());
        try {
            authenticationService.logout(logoutRequest);
            return ResponseBuilder.buildResponse(null, ErrorCode.SUCCESS);
        } catch (AppException ex) {
            log.error("Request logout fail");
            return ResponseBuilder.buildResponse(null, ex.getErrorCode());
        }
    }


    @PostMapping("/verify-account")
    public ResponseEntity<APIResponse<Void>> verifyOtp(@RequestBody AccountVerifyRequest accountVerifyRequest) {
        log.info("OTP verification request for email: {}", accountVerifyRequest.getEmail());
        try {
            accountService.verifyAccountAfterRegister(accountVerifyRequest);
            return ResponseBuilder.buildResponse(null, ErrorCode.SUCCESS);
        } catch (AppException ex) {
            log.error("OTP verification failed for email: {}", accountVerifyRequest.getEmail(), ex);
            return ResponseBuilder.buildResponse(null, ex.getErrorCode());
        }
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<APIResponse<Void>> resendOTP(@RequestBody String email) {
        log.info("Request resend OTP with email: {}", email);
        try {
            accountService.resendOTP(email);
            return ResponseBuilder.buildResponse(null, ErrorCode.SUCCESS);
        } catch (AppException ex) {
            log.error("OTP verification failed for email: {}", email, ex);
            return ResponseBuilder.buildResponse(null, ex.getErrorCode());
        }
    }
}
