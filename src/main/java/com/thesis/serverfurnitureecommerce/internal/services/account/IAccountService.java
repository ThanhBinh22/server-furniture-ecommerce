package com.thesis.serverfurnitureecommerce.internal.services.account;

import com.thesis.serverfurnitureecommerce.domain.request.AccountVerifyRequest;
import com.thesis.serverfurnitureecommerce.domain.request.LogoutRequest;
import com.thesis.serverfurnitureecommerce.domain.request.RegisterRequest;

public interface IAccountService {
    RegisterRequest RegisterAccount(RegisterRequest registerRequest);

    Boolean verifyAccountAfterRegister(AccountVerifyRequest accountVerifyRequest);

    void resendOTP(String email);

}
