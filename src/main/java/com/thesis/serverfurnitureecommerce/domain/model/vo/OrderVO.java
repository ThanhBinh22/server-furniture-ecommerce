package com.thesis.serverfurnitureecommerce.domain.model.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderVO {
    Long id;
    Double totalAmount;
    String status;
    String payment;
    Long userId;
    List<OrderItemVO> orderItemVOS;
    AddressVO address;
}
