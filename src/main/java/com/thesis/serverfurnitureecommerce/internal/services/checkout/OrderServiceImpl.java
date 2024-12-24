package com.thesis.serverfurnitureecommerce.internal.services.checkout;

import com.thesis.serverfurnitureecommerce.domain.request.OrderRequest;
import com.thesis.serverfurnitureecommerce.internal.repositories.OrderRepository;
import com.thesis.serverfurnitureecommerce.internal.repositories.ProductRepository;
import com.thesis.serverfurnitureecommerce.internal.repositories.UserRepository;
import com.thesis.serverfurnitureecommerce.model.dto.AddressDTO;
import com.thesis.serverfurnitureecommerce.model.dto.OrderItemDTO;
import com.thesis.serverfurnitureecommerce.model.entity.AddressEntity;
import com.thesis.serverfurnitureecommerce.model.entity.OrderEntity;
import com.thesis.serverfurnitureecommerce.model.entity.OrderItemEntity;
import com.thesis.serverfurnitureecommerce.model.entity.ProductEntity;
import com.thesis.serverfurnitureecommerce.pkg.enums.StatusOrderEnum;
import com.thesis.serverfurnitureecommerce.pkg.utils.UserUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceImpl implements OrderService{
    OrderRepository orderRepository;
    UserRepository userRepository;
    ProductRepository productRepository;

    @Override
    public void saveOrder(OrderRequest orderRequest) {
        String username = UserUtil.getUsername();
        OrderEntity order = OrderEntity.create();
        order.setStatus(StatusOrderEnum.PENDING);
        order.setTotalAmount(orderRequest.getTotalAmount());
        order.setPayment(order.getPayment());
        order.setUser(userRepository.findByUsername(username).orElse(null));
        for (OrderItemDTO orderItemDTO : orderRequest.getOrderItems()) {
            OrderItemDTO item = OrderItemDTO.create();
            item.setQuantity(orderItemDTO.getQuantity());
            item.setProductId(orderItemDTO.getProductId());
            item.setPrice(orderItemDTO.getPrice());

        }
        orderRepository.save(order);
    }

    @Override
    public void saveOrderItem() {

    }

    @Override
    public void getOrder() {

    }

    @Override
    public void getOrderItem() {

    }

    @Override
    public void deleteOrder() {

    }

    @Override
    public void deleteOrderItem() {

    }

    @Override
    public void updateOrder() {

    }

    @Override
    public void updateOrderItem() {

    }
}
