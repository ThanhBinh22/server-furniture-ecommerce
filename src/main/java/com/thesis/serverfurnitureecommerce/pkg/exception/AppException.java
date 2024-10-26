package com.thesis.serverfurnitureecommerce.pkg.exception;

import lombok.Getter;

/**
 * Custom exception class for application-specific errors.
 * This class extends RuntimeException to allow unchecked exceptions
 * to be thrown with an associated error code.
 */
@Getter
public class AppException extends RuntimeException {
    private final ErrorCode errorCode;

    /**
     * Constructs a new AppException with the specified error code.
     *
     * @param errorCode the error code associated with this exception
     */
    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
