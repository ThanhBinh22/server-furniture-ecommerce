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

public class LogoutRequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Test token is blank")
    void testTokenBlank() {
        LogoutRequest request = LogoutRequest.builder()
                .token("")
                .build();

        Set<ConstraintViolation<LogoutRequest>> violations = validator.validate(request);
        assertEquals(1, violations.size());
        assertEquals("PARAMETER_MISSING", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("Test valid LogoutRequest")
    void testValidRequest() {
        LogoutRequest request = LogoutRequest.builder()
                .token("validToken123")
                .build();

        Set<ConstraintViolation<LogoutRequest>> violations = validator.validate(request);
        assertEquals(0, violations.size());
    }
}
