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

public class AccountVerifyRequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Test email is blank")
    void testEmailBlank() {
        AccountVerifyRequest request = AccountVerifyRequest.builder()
                .email("")
                .otp("123456")
                .build();

        Set<ConstraintViolation<AccountVerifyRequest>> violations = validator.validate(request);
        assertEquals(1, violations.size());
        assertEquals("PARAMETER_MISSING", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("Test OTP is blank")
    void testOtpBlank() {
        AccountVerifyRequest request = AccountVerifyRequest.builder()
                .email("test@example.com")
                .otp("")
                .build();

        Set<ConstraintViolation<AccountVerifyRequest>> violations = validator.validate(request);
        assertEquals(1, violations.size());
        assertEquals("PARAMETER_MISSING", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("Test valid AccountVerifyRequest")
    void testValidRequest() {
        AccountVerifyRequest request = AccountVerifyRequest.builder()
                .email("test@example.com")
                .otp("123456")
                .build();

        Set<ConstraintViolation<AccountVerifyRequest>> violations = validator.validate(request);
        assertEquals(0, violations.size());
    }
}
