package com.thesis.serverfurnitureecommerce.presentation.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponse {
    Long orderID;
    String status;
    String paymentMethod;
    String urlPayment;
}
