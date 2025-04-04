package com.thesis.serverfurnitureecommerce.presentation.requestv2;

import jakarta.validation.constraints.*;

public record CustomerRegisterRequest(
        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
        @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain letters, numbers, and underscores (_)")
        String username,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,20}$",
                message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character (@$!%*?&)"
        )
        String password,

        @NotNull(message = "Type code is required")
        @Min(value = 0, message = "Invalid type code")
        @Max(value = 2, message = "Invalid type code")
        Integer typeCode
) {
        public CustomerType getCustomerType() {
                return CustomerType.fromCode(typeCode);
        }
}

enum CustomerType {
        USER(0),
        ADMIN(1),
        VENDOR(2);

        private final int code;

        CustomerType(int code) {
                this.code = code;
        }

        public int getCode() {
                return code;
        }

        public static CustomerType fromCode(int code) {
                for (CustomerType type : values()) {
                        if (type.getCode() == code) {
                                return type;
                        }
                }
                throw new IllegalArgumentException("Invalid type code: " + code);
        }
}
