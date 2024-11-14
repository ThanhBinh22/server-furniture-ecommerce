package com.thesis.serverfurnitureecommerce.configs;

import com.thesis.serverfurnitureecommerce.internal.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/**
 * Web configuration class for security-related beans.
 * <p>
 * This class defines the configuration for user authentication,
 * including the UserDetailsService, PasswordEncoder, and AuthenticationManager.
 * It utilizes a DAO-based authentication provider for handling user authentication.
 * </p>
 */
@Configuration
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WebConfig {
    UserRepository userRepository;

    /**
     * Constructs a WebConfig instance with the given IUserRepository.
     *
     * @param userRepository the user repository to be used for user lookups
     */
    public WebConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Provides a UserDetailsService bean for loading user-specific data.
     *
     * @return a UserDetailsService implementation
     * @throws UsernameNotFoundException if the user is not found
     */
    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Provides a PasswordEncoder bean that uses BCrypt for password hashing.
     *
     * @return a PasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides an AuthenticationManager bean for authenticating users.
     *
     * @param config the AuthenticationConfiguration to obtain the AuthenticationManager
     * @return the configured AuthenticationManager
     * @throws Exception if an error occurs while obtaining the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Provides an AuthenticationProvider bean that uses DAO-based authentication.
     *
     * @return a DaoAuthenticationProvider configured with UserDetailsService and PasswordEncoder
     */
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}
