package com.thesis.serverfurnitureecommerce.internal.controllers.authentication;

import com.thesis.serverfurnitureecommerce.internal.services.authentication.CustomOAuth2UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth2")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OAuth2Controller {
    CustomOAuth2UserService customOAuth2UserService;

}
