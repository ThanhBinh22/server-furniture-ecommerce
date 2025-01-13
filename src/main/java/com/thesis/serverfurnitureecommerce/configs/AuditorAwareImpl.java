package com.thesis.serverfurnitureecommerce.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * Lớp `AuditorAwareImpl` cung cấp thông tin về người dùng hiện tại
 * trong hệ thống, sử dụng cho cơ chế audit của Spring Data JPA.
 *
 * <p>Sử dụng {@link SecurityContextHolder} để lấy tên người dùng đang xác thực.
 * Trả về {@link Optional#empty()} nếu không có người dùng nào được xác thực.
 */

@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            log.warn("No authenticated user found");
            return Optional.empty();
        }
        log.info("Current auditor: {}", authentication.getName());
        return Optional.of(authentication.getName());
    }
}