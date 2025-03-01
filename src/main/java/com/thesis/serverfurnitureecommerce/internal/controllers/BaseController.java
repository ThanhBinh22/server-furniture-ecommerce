package com.thesis.serverfurnitureecommerce.internal.controllers;

import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.domain.response.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
public abstract class BaseController {

    protected <T> ResponseEntity<APIResponse<T>> handleAction(Action<T> action) {
        try {
            return action.execute();
        } catch (AppException ex) {
            log.error("❌ Error during action: {}", ex.getMessage());
            return ResponseBuilder.buildResponse(null, ex.getErrorCode());
        }
    }

    @FunctionalInterface
    protected interface Action<T> {
        ResponseEntity<APIResponse<T>> execute();
    }
}
