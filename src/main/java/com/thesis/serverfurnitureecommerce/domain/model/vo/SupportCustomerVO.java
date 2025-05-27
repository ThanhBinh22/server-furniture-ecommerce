package com.thesis.serverfurnitureecommerce.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupportCustomerVO {
    String title;
    String email;
    String message;
    String feedback;
    Boolean isSolve;
}
