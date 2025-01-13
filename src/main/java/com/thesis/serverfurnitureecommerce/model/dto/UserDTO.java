package com.thesis.serverfurnitureecommerce.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    Long id;
    String username;
    String fullName;
    String email;
    String phone;
    Short isLocked;
    Short isActive;
    RoleDTO role;
    LocalDateTime otpExpired;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}