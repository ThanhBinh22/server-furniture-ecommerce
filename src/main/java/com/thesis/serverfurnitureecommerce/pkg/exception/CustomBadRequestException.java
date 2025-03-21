package com.thesis.serverfurnitureecommerce.pkg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad Request")
public class CustomBadRequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;
}
