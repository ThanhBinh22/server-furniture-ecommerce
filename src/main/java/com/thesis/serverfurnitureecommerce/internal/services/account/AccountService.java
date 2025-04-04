package com.thesis.serverfurnitureecommerce.internal.services.account;

import com.thesis.serverfurnitureecommerce.domain.requestv2.RegisterRequest;

public interface AccountService {

    void registerAccount(RegisterRequest registerRequest);

    Boolean verifyAccountAfterRegister(String otp);

    void resendOTP(String email);

    boolean isAccountVerified(String email);
}
