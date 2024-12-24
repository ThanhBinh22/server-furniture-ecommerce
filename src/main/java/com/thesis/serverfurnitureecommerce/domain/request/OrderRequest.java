package com.thesis.serverfurnitureecommerce.domain.request;

import com.thesis.serverfurnitureecommerce.model.dto.AddressDTO;
import com.thesis.serverfurnitureecommerce.model.dto.OrderItemDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderRequest {
    Integer quantity;
    Double totalAmount;
    String phone;
    String message;
    String paymentMethod;
    AddressDTO address;
    List<OrderItemDTO> orderItems;
}
