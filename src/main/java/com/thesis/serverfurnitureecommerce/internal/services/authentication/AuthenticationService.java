package com.thesis.serverfurnitureecommerce.internal.services.authentication;

import com.thesis.serverfurnitureecommerce.domain.requestv2.AuthenticationRequest;
import com.thesis.serverfurnitureecommerce.domain.requestv2.LogoutRequest;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;

public interface AuthenticationService {
    UserEntity authenticate(AuthenticationRequest authenticationRequest);

    void logout(LogoutRequest logoutRequest);
}
