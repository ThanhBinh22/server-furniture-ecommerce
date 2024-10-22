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
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationController {
    IAccountService accountService;
    JwtService jwtService;
    IAuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public ResponseEntity<APIResponse<Void>> register(@RequestBody @Valid RegisterRequest registerRequest) {
        log.info("Registering user with username: {}", registerRequest.getUsername());
        return handleRequest(() -> {
            accountService.RegisterAccount(registerRequest);
            return ResponseBuilder.buildResponse(null, ErrorCode.CREATE_SUCCESS);
        });
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<LoginResponse>> authenticate(@RequestBody AuthenticationRequest login) {
        log.info("Requesting login for user: {}", login.getUsername());
        UserEntity authenticatedUser = authenticationService.authenticate(login);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse()
                .setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationTime());

        return ResponseBuilder.buildResponse(loginResponse, ErrorCode.SUCCESS);
    }

    @PostMapping("/logout")
    public ResponseEntity<APIResponse<Void>> logout(@RequestBody @Valid LogoutRequest logoutRequest) {
        log.info("Requesting logout for token: {}", logoutRequest.getToken());
        return handleRequest(() -> {
            authenticationService.logout(logoutRequest);
            return ResponseBuilder.buildResponse(null, ErrorCode.SUCCESS);
        });
    }

    @PostMapping("/confirm-account")
    public ResponseEntity<APIResponse<Void>> verifyOtp(@RequestParam String otp, HttpServletResponse response) {
        log.info("Verifying OTP: {}", otp);
        try {
            accountService.verifyAccountAfterRegister(otp);
            response.sendRedirect("http://localhost:5173/sign-in");
            return ResponseBuilder.buildResponse(null, ErrorCode.CREATE_SUCCESS);
        } catch (IOException e) {
            log.error("Failed to redirect after OTP verification", e);
            throw new RuntimeException("Redirect failed", e);
        }
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<APIResponse<Void>> resendOtp(@RequestBody String email) {
        log.info("Requesting OTP resend for email: {}", email);
        return handleRequest(() -> {
            accountService.resendOTP(email);
            return ResponseBuilder.buildResponse(null, ErrorCode.SUCCESS);
        });
    }

    private <T> ResponseEntity<APIResponse<T>> handleRequest(RequestHandler<T> handler) {
        try {
            return handler.execute();
        } catch (AppException ex) {
            log.error("Error occurred: {}", ex.getErrorCode(), ex);
            return ResponseBuilder.buildResponse(null, ex.getErrorCode());
        }
    }

    @FunctionalInterface
    private interface RequestHandler<T> {
        ResponseEntity<APIResponse<T>> execute();
    }
}
