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
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegisterRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Test valid register request")
    void testValidRegisterRequest() {
        RegisterRequest request = RegisterRequest.builder()
                .username("testuser")
                .email("test@example.com")
                .password("Password123!")
                .fullName("John Doe")
                .build();

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "Should not have validation errors");
    }

    @Test
    @DisplayName("Test username is blank")
    void testUsernameBlank() {
        RegisterRequest request = RegisterRequest.builder()
                .username("")  // Username is blank
                .email("test@example.com")
                .password("Password123!")
                .fullName("John Doe")
                .build();

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);
        violations.forEach(violation -> {
            System.out.println("Property: " + violation.getPropertyPath());
            System.out.println("Message: " + violation.getMessage());
        });
        assertEquals(2, violations.size(), "Should have two validation errors");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Username is required")));
    }


    @Test
    @DisplayName("Test username is too short")
    void testEmailInvalid() {
        RegisterRequest request = RegisterRequest.builder()
                .username("testuser")
                .email("invalid-email")
                .password("Password123!")
                .fullName("John Doe")
                .build();

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);
        assertEquals(1, violations.size());
        assertEquals("Email should be valid", violations.iterator().next().getMessage());
    }

    @Test
    void testPasswordInvalid() {
        RegisterRequest request = RegisterRequest.builder()
                .username("testuser")
                .email("test@example.com")
                .password("pass")
                .fullName("John Doe")
                .build();

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);
        assertEquals(1, violations.size());
        assertEquals("Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character.",
                violations.iterator().next().getMessage());
    }

    @Test
    void testFullNameBlank() {
        RegisterRequest request = RegisterRequest.builder()
                .username("testuser")
                .email("test@example.com")
                .password("Password123!")
                .fullName("")
                .build();

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);
        assertEquals(1, violations.size());
        assertEquals("Full name is required", violations.iterator().next().getMessage());
    }
}
