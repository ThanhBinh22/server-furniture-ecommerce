package com.thesis.serverfurnitureecommerce.application.services.user;

import com.thesis.serverfurnitureecommerce.domain.model.vo.UserVO;
import com.thesis.serverfurnitureecommerce.presentation.requestv2.AccountVerifyRequest;
import com.thesis.serverfurnitureecommerce.presentation.requestv2.NewPasswordRequest;
import com.thesis.serverfurnitureecommerce.presentation.requestv2.UpdateAccountRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void forgotPassword(String email);

    void verifyForgetPassword(AccountVerifyRequest accountVerifyRequest);

    void changePassword(NewPasswordRequest newPasswordRequest);

    void deleteAccount(UUID userID);

    UpdateAccountRequest updateProfile(UpdateAccountRequest updateAccountRequest);

    UserVO viewProfile(UUID userID);

    UserVO getInformationUser(String accessToken);

    void blockUser(UUID userID);

    List<UserVO> getAllUser();

}
