package com.thesis.serverfurnitureecommerce.domain.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginResponseTest {

    @Test
    @DisplayName("Should create LoginResponse with valid token and expiration")
    public void testLoginResponseCreation() {
        LoginResponse response = LoginResponse.builder()
                .token("myToken123")
                .expiresIn(3600)
                .build();

        assertEquals("myToken123", response.getToken());
        assertEquals(3600, response.getExpiresIn());
    }

    @Test
    @DisplayName("Should allow chaining methods for LoginResponse")
    public void testLoginResponseChaining() {
        LoginResponse response = new LoginResponse()
                .setToken("myToken123")
                .setExpiresIn(3600);

        assertEquals("myToken123", response.getToken());
        assertEquals(3600, response.getExpiresIn());
    }

    @Test
    @DisplayName("Should create LoginResponse with default constructor")
    public void testLoginResponseDefaultConstructor() {
        LoginResponse response = new LoginResponse();

        assertNull(response.getToken());
        assertEquals(0, response.getExpiresIn());
    }
}
