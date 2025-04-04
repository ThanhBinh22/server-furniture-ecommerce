package com.thesis.serverfurnitureecommerce.application.services.authentication;

import com.thesis.serverfurnitureecommerce.infrastructure.persistence.UserRepository;
import com.thesis.serverfurnitureecommerce.domain.model.entity.UserEntity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = fetchOAuth2User(userRequest);
        String email = oAuth2User.getAttribute("email");

        registerUserIfNotExists(oAuth2User, userRequest, email);

        return oAuth2User;
    }

    private OAuth2User fetchOAuth2User(OAuth2UserRequest userRequest) {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        return delegate.loadUser(userRequest);
    }

    private void registerUserIfNotExists(OAuth2User oAuth2User, OAuth2UserRequest userRequest, String email) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return;
        }
        UserEntity user = createUserEntity(oAuth2User, userRequest, email);
        userRepository.save(user);
    }

    private UserEntity createUserEntity(OAuth2User oAuth2User, OAuth2UserRequest userRequest, String email) {
        UserEntity user = UserEntity.createNewUserEntity();
        user.setEmail(email);
        user.setFullName(oAuth2User.getAttribute("name"));
        user.setOauth2Provider(userRequest.getClientRegistration().getRegistrationId());
        return user;
    }
}
