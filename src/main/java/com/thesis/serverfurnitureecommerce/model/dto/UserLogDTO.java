package com.thesis.serverfurnitureecommerce.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLogDTO {
    Long id;
    String level;
    String ip;
    String action;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    UserDTO user;
}
