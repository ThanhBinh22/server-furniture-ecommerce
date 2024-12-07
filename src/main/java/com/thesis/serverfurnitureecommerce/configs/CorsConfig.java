package com.thesis.serverfurnitureecommerce.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Configuration class for setting up CORS (Cross-Origin Resource Sharing) in the application.
 * <p>
 * This configuration allows all origins, methods, and headers to enable seamless cross-origin requests.
 * </p>
 * <p>
 * It registers the CORS configuration for all endpoints in the application.
 * </p>
 */
@Configuration("corsConfig")
public class CorsConfig {
    /**
     * Bean for CORS configuration source.
     *
     * @return CorsConfigurationSource that allows all origins, methods, and headers.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}