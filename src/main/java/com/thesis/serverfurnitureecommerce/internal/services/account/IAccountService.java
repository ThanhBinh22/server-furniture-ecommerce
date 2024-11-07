package com.thesis.serverfurnitureecommerce.internal.services.account;

import com.thesis.serverfurnitureecommerce.domain.request.RegisterRequest;

public interface IAccountService {

    void RegisterAccount(RegisterRequest registerRequest);

    Boolean verifyAccountAfterRegister(String otp);

    void resendOTP(String email);

    boolean isAccountVerified(String email);
}
