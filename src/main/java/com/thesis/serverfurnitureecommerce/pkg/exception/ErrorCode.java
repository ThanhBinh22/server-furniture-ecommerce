package com.thesis.serverfurnitureecommerce.pkg.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Enum representing error codes for application-specific errors.
 * Each error code has an associated HTTP status code and a message.
 */
@Getter
public enum ErrorCode {

    // Success
    SUCCESS(HttpStatus.OK.value(), "Success"),
    VERIFY_FAIL(HttpStatus.BAD_REQUEST.value(), "Verify fail"),
    DELETE_SUCCESS(HttpStatus.OK.value(), "Delete success"),
    CREATE_SUCCESS(HttpStatus.CREATED.value(), "Create success"),
    UPDATE_SUCCESS(HttpStatus.OK.value(), "Update success"),
    FOUND(HttpStatus.OK.value(), "Found"),

    // Client Errors
    INVALID_REQUEST(HttpStatus.BAD_REQUEST.value(), "Invalid request"),
    PARAMETER_NOT_VALID(HttpStatus.BAD_REQUEST.value(), "Parameter not valid or validation error"),
    PARAMETER_MISSING(HttpStatus.BAD_REQUEST.value(), "Parameter missing"),
    DELETE_FAILED(HttpStatus.BAD_REQUEST.value(), "Delete failed"),
    CREATE_FAILED(HttpStatus.BAD_REQUEST.value(), "Create failed"),
    UPDATE_FAILED(HttpStatus.BAD_REQUEST.value(), "Update failed"),
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED.value(), "Unauthenticated"),
    UNAUTHORIZED(HttpStatus.FORBIDDEN.value(), "You do not have permission"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "User not found"),
    USER_NOT_ENABLED(HttpStatus.BAD_REQUEST.value(), "Account not verified"),
    USER_LOCKED(HttpStatus.BAD_REQUEST.value(), "Account has been locked"),
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Not found"),
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT.value(), "Email already exists"),
    USERNAME_ALREADY_EXISTS(HttpStatus.CONFLICT.value(), "Username already exists"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST.value(), "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character"),
    ROLE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Role not found"),
    OTP_EXPIRED(HttpStatus.BAD_REQUEST.value(), "OTP expired"),
    INVALID_OTP(HttpStatus.BAD_REQUEST.value(), "Invalid OTP"),
    ACCOUNT_NOT_VERIFIED(HttpStatus.BAD_REQUEST.value(), "Account not verified"),
    CART_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "Cart not found"),
    CART_ITEM_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "Cart item not found"),
    USER_NOT_AUTHENTICATE(HttpStatus.BAD_REQUEST.value(), "User not authenticate"),
    INVALID_CREDENTIALS(HttpStatus.BAD_REQUEST.value(), "mật khẩu không chính xác"),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST.value(), "Email chưa được đăng ký tài khoản"),
    TOKEN_EXPIRED(HttpStatus.BAD_REQUEST.value(), "Token expired"),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST.value(), "Invalid token"),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Product not found"),
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Review not found"),
    CART_ITEM_EMPTY(HttpStatus.OK.value(), "Cart item empty"),
    // Server Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error"),
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE.value(), "Service Unavailable"),
    GATEWAY_TIMEOUT(HttpStatus.GATEWAY_TIMEOUT.value(), "Gateway Timeout");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

