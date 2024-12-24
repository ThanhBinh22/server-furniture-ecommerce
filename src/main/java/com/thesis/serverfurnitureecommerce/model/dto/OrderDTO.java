package com.thesis.serverfurnitureecommerce.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderDTO {
    Long id;
    Double totalAmount;
    String status;
    String payment;
    Long userId;
    List<OrderItemDTO> orderItemDTOs;
    AddressDTO address;
}
