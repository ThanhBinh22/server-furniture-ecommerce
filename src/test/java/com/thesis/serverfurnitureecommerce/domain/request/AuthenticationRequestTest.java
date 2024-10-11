package com.thesis.serverfurnitureecommerce.domain.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthenticationRequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Test username is blank")
    void testUsernameBlank() {
        AuthenticationRequest request = AuthenticationRequest.builder()
                .username("")
                .password("ValidPassword123!")
                .build();

        Set<ConstraintViolation<AuthenticationRequest>> violations = validator.validate(request);
        assertEquals(1, violations.size());
        assertEquals("PARAMETER_MISSING", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("Test password is blank")
    void testPasswordBlank() {
        AuthenticationRequest request = AuthenticationRequest.builder()
                .username("validUsername")
                .password("")
                .build();

        Set<ConstraintViolation<AuthenticationRequest>> violations = validator.validate(request);
        assertEquals(1, violations.size());
        assertEquals("PARAMETER_MISSING", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("Test valid authentication request")
    void testValidRequest() {
        AuthenticationRequest request = AuthenticationRequest.builder()
                .username("validUsername")
                .password("ValidPassword123!")
                .build();

        Set<ConstraintViolation<AuthenticationRequest>> violations = validator.validate(request);
        assertEquals(0, violations.size());
    }
}

