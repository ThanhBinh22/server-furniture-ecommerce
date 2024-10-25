package com.thesis.serverfurnitureecommerce.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupportCustomerDTO {
    String title;
    String email;
    String message;
    String feedback;
    Boolean isSolve;
}
