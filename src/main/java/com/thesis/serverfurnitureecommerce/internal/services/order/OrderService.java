package com.thesis.serverfurnitureecommerce.internal.services.order;

import com.thesis.serverfurnitureecommerce.domain.request.OrderRequest;

public interface OrderService {
    void createOrder(OrderRequest orderRequest);
    void updateOrder(Long orderID, int status);
}
