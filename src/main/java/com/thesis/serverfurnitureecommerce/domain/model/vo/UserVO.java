package com.thesis.serverfurnitureecommerce.domain.model.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserVO {
    UUID id;
    String username;
    String fullName;
    String email;
    String phone;
    Short isLocked;
    Short isActive;
    RoleVO role;
    LocalDateTime otpExpired;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}