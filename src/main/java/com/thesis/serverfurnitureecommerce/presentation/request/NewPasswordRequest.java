package com.thesis.serverfurnitureecommerce.presentation.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Deprecated
public class NewPasswordRequest {
    @NotBlank(message = "PARAMETER_MISSING")
    String email;
    @NotBlank(message = "PARAMETER_MISSING")
    String password;
}
