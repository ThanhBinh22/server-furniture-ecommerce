package com.thesis.serverfurnitureecommerce.presentation.controllers.authentication;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OAuth2Controller {

    @GetMapping("/google/callback")
    public ResponseEntity<?> handleGoogleCallback(OAuth2AuthenticationToken authentication) {
        // Retrieve user info
        OAuth2User user = authentication.getPrincipal();
        String email = user.getAttribute("email");
        String name = user.getAttribute("name");
        return ResponseEntity.ok(null);
    }
}
