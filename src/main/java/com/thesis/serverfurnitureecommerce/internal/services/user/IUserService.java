package com.thesis.serverfurnitureecommerce.internal.services.user;

import com.thesis.serverfurnitureecommerce.model.dto.UserDTO;

public interface IUserService {
    UserDTO getUserInformation(Long userID);
}
