package com.thesis.serverfurnitureecommerce.presentation.response;

import com.thesis.serverfurnitureecommerce.domain.model.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private UserVO user;
    private long expiresIn;
}
