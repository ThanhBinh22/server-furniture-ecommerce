package com.thesis.serverfurnitureecommerce.internal.controllers.authentication;

import com.thesis.serverfurnitureecommerce.domain.requestv2.AuthenticationRequest;
import com.thesis.serverfurnitureecommerce.domain.requestv2.LogoutRequest;
import com.thesis.serverfurnitureecommerce.domain.requestv2.RefreshTokenRequest;
import com.thesis.serverfurnitureecommerce.domain.requestv2.RegisterRequest;
import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.domain.response.LoginResponse;
import com.thesis.serverfurnitureecommerce.pkg.utils.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.controllers.BaseController;
import com.thesis.serverfurnitureecommerce.internal.services.account.AccountService;
import com.thesis.serverfurnitureecommerce.internal.services.authentication.AuthenticationService;
import com.thesis.serverfurnitureecommerce.internal.services.jwt.JwtService;
import com.thesis.serverfurnitureecommerce.internal.services.logs.UserLogService;
import com.thesis.serverfurnitureecommerce.internal.services.token.RefreshTokenService;
import com.thesis.serverfurnitureecommerce.model.dto.UserDTO;
import com.thesis.serverfurnitureecommerce.model.entity.RefreshTokenEntity;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.pkg.mapper.UserMapper;
import com.thesis.serverfurnitureecommerce.pkg.utils.annotation.ApiMessage;
import jakarta.servlet.http.HttpServletRequest;
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
public class AuthenticationController extends BaseController {
    AccountService accountService;
    JwtService jwtService;
    AuthenticationService authenticationService;
    UserMapper userMapper;
    RefreshTokenService refreshTokenService;
    UserLogService userLogService;

    @PostMapping("/sign-up")
    public ResponseEntity<APIResponse<Void>> register(@RequestBody @Valid RegisterRequest registerRequest) {
        return handleAction(() -> {
            accountService.registerAccount(registerRequest);
            return ResponseBuilder.buildResponse(null, ErrorCode.CREATE_SUCCESS);
        });
    }

    @ApiMessage("Login")
    @PostMapping("/login")
    public ResponseEntity<APIResponse<LoginResponse>> authenticate(@RequestBody AuthenticationRequest login, HttpServletRequest httpRequest) {
        log.info("Requesting login for user: {}", login.username());
        UserEntity authenticatedUser = authenticationService.authenticate(login);
        userLogService.log("Login","INFO", "User require login account", login.username(), httpRequest.getRemoteAddr());

        String jwtToken = jwtService.generateToken(authenticatedUser);
        RefreshTokenEntity refreshToken = refreshTokenService.createRefreshToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse()
                .setAccessToken(jwtToken)
                .setRefreshToken(refreshToken.getTokenId())
                .setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setUser(userMapper.toDTO(authenticatedUser));

        return ResponseBuilder.buildResponse(loginResponse, ErrorCode.SUCCESS);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<LoginResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        String requestRefreshToken = refreshTokenRequest.refreshToken();
        String newAccessToken = refreshTokenService.generateAccessTokenAgain(requestRefreshToken);
        UserDTO userDTO = refreshTokenService.getUserByRefreshToken(requestRefreshToken);
        return ResponseEntity.ok(new LoginResponse()
                .setAccessToken(newAccessToken)
                .setUser(userDTO)
                .setRefreshToken(requestRefreshToken)
                .setExpiresIn(jwtService.getExpirationTime())
        );
    }

    @ApiMessage("Logout")
    @PostMapping("/logout")
    public ResponseEntity<APIResponse<Void>> logout(@RequestBody @Valid LogoutRequest logoutRequest, HttpServletRequest httpServletRequest) {
        return handleAction(() -> {
            userLogService.log("Logout","INFO", "User require logout account", null, httpServletRequest.getRemoteAddr());
            authenticationService.logout(logoutRequest);
            return ResponseBuilder.buildResponse(null, ErrorCode.SUCCESS);
        });
    }

    @ApiMessage("Verify OTP")
    @PostMapping("/confirm-account")
    public ResponseEntity<APIResponse<Void>> verifyOtp(@RequestParam String otp) {
        try {
            accountService.verifyAccountAfterRegister(otp);
            return ResponseBuilder.buildResponse(null, ErrorCode.CREATE_SUCCESS);
        } catch (AppException ex) {
            log.error("Error occurred: {}", ex.getErrorCode(), ex);
            return ResponseBuilder.buildResponse(null, ex.getErrorCode());
        }
    }

    @ApiMessage("Check account verification status")
    @GetMapping("/check-account-verification-status")
    public ResponseEntity<Boolean> checkAccountVerificationStatus(@RequestParam String email) {
        boolean isVerified = accountService.isAccountVerified(email);
        return ResponseEntity.ok(isVerified);
    }


    @ApiMessage("Resend OTP")
    @PostMapping("/resend-otp")
    public ResponseEntity<APIResponse<Void>> resendOtp(@RequestBody String email) {
        return handleAction(() -> {
            accountService.resendOTP(email);
            return ResponseBuilder.buildResponse(null, ErrorCode.SUCCESS);
        });
    }
}
