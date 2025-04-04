package com.thesis.serverfurnitureecommerce.application.services.authentication;

import com.thesis.serverfurnitureecommerce.presentation.requestv2.AuthenticationRequest;
import com.thesis.serverfurnitureecommerce.presentation.requestv2.CustomerRegisterRequest;
import com.thesis.serverfurnitureecommerce.presentation.requestv2.LogoutRequest;
import com.thesis.serverfurnitureecommerce.presentation.response.UserInfo;
import com.thesis.serverfurnitureecommerce.domain.model.entity.UserEntity;

public interface AuthenticationService {
    UserEntity authenticate(AuthenticationRequest authenticationRequest);
    void logout(LogoutRequest logoutRequest);
    UserInfo signUp(CustomerRegisterRequest customerRegisterRequest);
}
