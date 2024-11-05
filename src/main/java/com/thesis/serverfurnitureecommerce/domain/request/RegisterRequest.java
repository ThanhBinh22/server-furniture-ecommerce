package com.thesis.serverfurnitureecommerce.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A request class for user registration.
 * <p>
 * This class encapsulates the information required to register a new user.
 * It includes the user's username, email, password, and full name,
 * along with validation constraints for each field.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    /**
     * The username of the user.
     * It must be between 3 and 20 characters and cannot be blank.
     */
    @NotBlank(message = "Tên người dùng đã tồn tại")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    /**
     * The email address of the user.
     * It must be a valid email format and cannot be blank.
     */
    @NotBlank(message = "Email bắt buộc")
    @Email(message = "Email should be valid")
    private String email;

    /**
     * The password for the user account.
     * It must be between 6 and 20 characters long, containing at least one
     * uppercase letter, one lowercase letter, one digit, and one special character.
     */
    @NotBlank(message = "Password bắt buộc")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,20}$",
            message = "Mật khẩu phải bao gồm ít nhất một chữ hoa, một chữ thường, một số và một ký tự đặc biệt!")
    private String password;

    /**
     * The full name of the user.
     * This field cannot be blank.
     */
    @NotBlank(message = "Full name is required")
    private String fullName;
}
