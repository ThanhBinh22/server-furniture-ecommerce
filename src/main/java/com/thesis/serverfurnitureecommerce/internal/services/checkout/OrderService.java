package com.thesis.serverfurnitureecommerce.internal.services.checkout;

import com.thesis.serverfurnitureecommerce.domain.request.OrderRequest;

public interface OrderService {
    void saveOrder(OrderRequest orderRequest);
    void saveOrderItem();
    void getOrder();
    void getOrderItem();
    void deleteOrder();
    void deleteOrderItem();
    void updateOrder();
    void updateOrderItem();
}
