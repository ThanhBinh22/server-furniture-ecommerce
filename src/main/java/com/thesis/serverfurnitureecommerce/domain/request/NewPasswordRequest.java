package com.thesis.serverfurnitureecommerce.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewPasswordRequest {
    @NotBlank(message = "PARAMETER_MISSING")
    String email;
    @NotBlank(message = "PARAMETER_MISSING")
    String newPassword;
}
