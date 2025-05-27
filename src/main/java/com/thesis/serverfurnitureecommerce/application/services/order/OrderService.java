package com.thesis.serverfurnitureecommerce.application.services.order;

import com.thesis.serverfurnitureecommerce.presentation.request.OrderRequest;

public interface OrderService {
    void createOrder(OrderRequest orderRequest);
    void updateOrder(Long orderID, int status);
}
