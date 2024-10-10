package com.thesis.serverfurnitureecommerce.internal.services.authentication;

import com.thesis.serverfurnitureecommerce.internal.repositories.IUserRepository;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private IUserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");

        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            UserEntity user = new UserEntity();
            user.setEmail(email);
            user.setFullName(oAuth2User.getAttribute("name"));
            user.setOauth2Provider(userRequest.getClientRegistration().getRegistrationId());
            user.setEmail(email);

            userRepository.save(user);
        }

        return oAuth2User;
    }
}
