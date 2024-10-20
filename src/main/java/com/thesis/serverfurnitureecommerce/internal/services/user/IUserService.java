package com.thesis.serverfurnitureecommerce.internal.services.user;

import com.thesis.serverfurnitureecommerce.domain.request.AccountVerifyRequest;
import com.thesis.serverfurnitureecommerce.domain.request.NewPasswordRequest;
import com.thesis.serverfurnitureecommerce.model.dto.UserDTO;

public interface IUserService {
    void forgotPassword(String email);

    boolean verifyForgetPassword(AccountVerifyRequest accountVerifyRequest);

    void changePassword(NewPasswordRequest newPasswordRequest);
}
