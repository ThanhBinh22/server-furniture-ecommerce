package com.thesis.serverfurnitureecommerce.presentation.controllers;

import com.thesis.serverfurnitureecommerce.presentation.response.APIResponse;
import com.thesis.serverfurnitureecommerce.common.utils.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.domain.exception.AppException;
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
