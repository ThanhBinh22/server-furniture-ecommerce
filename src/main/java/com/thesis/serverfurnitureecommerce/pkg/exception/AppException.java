package com.thesis.serverfurnitureecommerce.pkg.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * Custom exception class for application-specific errors.
 * This class extends RuntimeException to allow unchecked exceptions
 * to be thrown with an associated error code.
 */
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppException extends RuntimeException {
    ErrorCode errorCode;

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
