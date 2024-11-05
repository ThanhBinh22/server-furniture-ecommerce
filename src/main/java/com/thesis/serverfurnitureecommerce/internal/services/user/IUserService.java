package com.thesis.serverfurnitureecommerce.internal.services.user;

import com.thesis.serverfurnitureecommerce.domain.request.AccountVerifyRequest;
import com.thesis.serverfurnitureecommerce.domain.request.NewPasswordRequest;

public interface IUserService {

    void forgotPassword(String email);

    void verifyForgetPassword(AccountVerifyRequest accountVerifyRequest);

    void changePassword(NewPasswordRequest newPasswordRequest);

}
