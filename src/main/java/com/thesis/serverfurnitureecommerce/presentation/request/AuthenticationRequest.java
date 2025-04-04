package com.thesis.serverfurnitureecommerce.presentation.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {

    @NotBlank(message = "PARAMETER_MISSING")
    String username;

    @NotBlank(message = "PARAMETER_MISSING")
    String password;
}
