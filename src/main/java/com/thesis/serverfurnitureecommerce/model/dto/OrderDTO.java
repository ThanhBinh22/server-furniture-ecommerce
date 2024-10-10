package com.thesis.serverfurnitureecommerce.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDTO {
     Long id;
     Double totalPrice;
     LocalDateTime createdAt;
     LocalDateTime updatedAt;
     String status;
     String paymentMethod;
     UserDTO user;
     Set<OrderItemDTO> orderItems;
}
