package com.thesis.serverfurnitureecommerce.presentation.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Deprecated
public class EmailRequest {
    String email;
}
