package com.thesis.serverfurnitureecommerce.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    String username;
    String phoneNumber;
    String email;
}
