package com.thesis.serverfurnitureecommerce.configs;

import com.thesis.serverfurnitureecommerce.internal.services.authentication.CustomOAuth2UserService;
import com.thesis.serverfurnitureecommerce.internal.services.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration class for the application.
 * <p>
 * This class configures the security settings for the application, including
 * authentication, authorization, and session management. It integrates JWT
 * authentication and OAuth2 login capabilities.
 * </p>
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfig {
    AuthenticationProvider authenticationProvider;
    JwtAuthenticationFilter jwtAuthenticationFilter;
    CustomOAuth2UserService customOAuth2UserService;

    /**
     * Configures the security filter chain for the application.
     * <p>
     * This method sets up HTTP security, including CSRF protection,
     * request authorization, OAuth2 login, and stateless session management.
     * </p>
     *
     * @param http the {@link HttpSecurity} to configure
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if any error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**", "/api/user/**", "/actuator/**", "/api/faqs").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        )
                        .defaultSuccessUrl("/home", true)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
