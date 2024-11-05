package com.thesis.serverfurnitureecommerce.domain.response;

import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import org.springframework.http.ResponseEntity;

/**
 * A utility class for building standardized API responses.
 *
 * This class provides a method to create a ResponseEntity
 * that encapsulates the response structure used in the API,
 * including the status code, message, and result data.
 */
public class ResponseBuilder {

    // Private constructor to prevent instantiation of the utility class
    private ResponseBuilder() {
        throw new AssertionError();
    }

    /**
     * Builds a standardized response entity.
     *
     * @param result the data to include in the response, can be null
     * @param errorCode the error code that corresponds to the response status
     * @param <T> the type of the result
     * @return a ResponseEntity containing an APIResponse with the specified result and error code
     */
    public static <T> ResponseEntity<APIResponse<T>> buildResponse(T result, ErrorCode errorCode) {
        APIResponse<T> response = APIResponse.<T>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .result(result)
                .build();
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
