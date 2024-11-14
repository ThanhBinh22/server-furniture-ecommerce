package com.thesis.serverfurnitureecommerce.internal.services.authentication;

import com.thesis.serverfurnitureecommerce.domain.request.AuthenticationRequest;
import com.thesis.serverfurnitureecommerce.domain.request.LogoutRequest;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;

public interface AuthenticationService {
    UserEntity authenticate(AuthenticationRequest authenticationRequest);

    void logout(LogoutRequest logoutRequest);
}
