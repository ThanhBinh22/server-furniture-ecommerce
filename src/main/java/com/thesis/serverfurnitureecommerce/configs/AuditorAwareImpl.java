package com.thesis.serverfurnitureecommerce.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * Implementation of the AuditorAware interface for retrieving the current auditor.
 * <p>
 * This class checks the Spring Security context to find the currently authenticated user
 * and uses their username as the auditor.
 * </p>
 * <p>
 * If no user is authenticated, it returns an empty Optional.
 * </p>
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