package com.thesis.serverfurnitureecommerce.pkg.utils;

import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {
    private ResponseBuilder() {
        throw new AssertionError();
    }
    public static <T> ResponseEntity<APIResponse<T>> buildResponse(T result, ErrorCode errorCode) {
        APIResponse<T> response = APIResponse.<T>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .result(result)
                .build();
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
