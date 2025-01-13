package com.thesis.serverfurnitureecommerce.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Cấu hình CORS (Cross-Origin Resource Sharing) cho ứng dụng.
 *
 * <p>Lớp này cho phép tùy chỉnh các nguồn gốc, phương thức và header
 * được phép trong các yêu cầu HTTP giữa các miền khác nhau.
 * Hiện tại, cấu hình cho phép tất cả nguồn gốc, phương thức và header.
 */
@Configuration("corsConfig")
public class CorsConfig {

    /**
     * Cung cấp cấu hình CORS cho ứng dụng.
     *
     * @return {@link CorsConfigurationSource} chứa thông tin cấu hình CORS.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*")); // Cho phép tất cả nguồn gốc.
        configuration.setAllowedMethods(List.of("*")); // Cho phép tất cả phương thức.
        configuration.setAllowedHeaders(List.of("*")); // Cho phép tất cả header.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Áp dụng cấu hình cho tất cả đường dẫn.
        return source;
    }

}
