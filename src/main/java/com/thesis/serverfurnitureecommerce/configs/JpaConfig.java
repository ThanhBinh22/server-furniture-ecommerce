package com.thesis.serverfurnitureecommerce.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Configuration class for enabling JPA auditing in the application.
 * <p>
 * This class registers an {@link AuditorAware} implementation to track the
 * user who created or modified an entity.
 * </p>
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaConfig {

    /**
     * Bean that provides the current auditor's information.
     *
     * @return an implementation of {@link AuditorAware} which returns
     * the username of the currently authenticated user.
     */
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
