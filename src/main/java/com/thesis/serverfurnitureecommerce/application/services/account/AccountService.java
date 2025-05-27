package com.thesis.serverfurnitureecommerce.application.services.account;

import com.thesis.serverfurnitureecommerce.presentation.requestv2.RegisterRequest;

public interface AccountService {

    void registerAccount(RegisterRequest registerRequest);

    Boolean verifyAccountAfterRegister(String otp);

    void resendOTP(String email);

    boolean isAccountVerified(String email);
}
