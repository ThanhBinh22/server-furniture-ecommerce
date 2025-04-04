package com.thesis.serverfurnitureecommerce.domain.responsev2;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record APIResponse<T>(
        Integer code,
        String message,
        T result
) {}