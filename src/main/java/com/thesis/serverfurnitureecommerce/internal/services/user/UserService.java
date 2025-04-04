package com.thesis.serverfurnitureecommerce.internal.services.user;

import com.thesis.serverfurnitureecommerce.domain.requestv2.AccountVerifyRequest;
import com.thesis.serverfurnitureecommerce.domain.requestv2.NewPasswordRequest;
import com.thesis.serverfurnitureecommerce.domain.requestv2.UpdateAccountRequest;
import com.thesis.serverfurnitureecommerce.model.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void forgotPassword(String email);

    void verifyForgetPassword(AccountVerifyRequest accountVerifyRequest);

    void changePassword(NewPasswordRequest newPasswordRequest);

    void deleteAccount(UUID userID);

    UpdateAccountRequest updateProfile(UpdateAccountRequest updateAccountRequest);

    UserDTO viewProfile(UUID userID);

    UserDTO getInformationUser(String accessToken);

    void blockUser(UUID userID);

    List<UserDTO> getAllUser();

}
