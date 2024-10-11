package com.thesis.serverfurnitureecommerce.domain.response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class APIResponseTest {

    @Test
    @DisplayName("Should create APIResponse with valid data")
    public void testApiResponseWithValidData() {
        APIResponse<String> response = APIResponse.<String>builder()
                .code(200)
                .message("Success")
                .result("This is a valid result.")
                .build();

        assertEquals(200, response.getCode());
        assertEquals("Success", response.getMessage());
        assertEquals("This is a valid result.", response.getResult());
    }

    @Test
    @DisplayName("Should create APIResponse with null result")
    public void testApiResponseWithNullResult() {
        APIResponse<String> response = APIResponse.<String>builder()
                .code(404)
                .message("Not Found")
                .result(null)
                .build();

        assertEquals(404, response.getCode());
        assertEquals("Not Found", response.getMessage());
        assertNull(response.getResult());
    }

    @Test
    @DisplayName("Should create APIResponse with empty message")
    public void testApiResponseWithEmptyMessage() {
        APIResponse<String> response = APIResponse.<String>builder()
                .code(400)
                .message("")
                .result("Some result data")
                .build();

        assertEquals(400, response.getCode());
        assertEquals("", response.getMessage());
        assertEquals("Some result data", response.getResult());
    }
}
