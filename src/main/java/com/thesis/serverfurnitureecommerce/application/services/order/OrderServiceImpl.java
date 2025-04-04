package com.thesis.serverfurnitureecommerce.application.services.order;

import com.thesis.serverfurnitureecommerce.presentation.request.OrderItemRequest;
import com.thesis.serverfurnitureecommerce.presentation.request.OrderRequest;
import com.thesis.serverfurnitureecommerce.infrastructure.persistence.OrderItemRepository;
import com.thesis.serverfurnitureecommerce.infrastructure.persistence.OrderRepository;
import com.thesis.serverfurnitureecommerce.infrastructure.persistence.ProductRepository;
import com.thesis.serverfurnitureecommerce.infrastructure.persistence.UserRepository;
import com.thesis.serverfurnitureecommerce.domain.model.entity.OrderEntity;
import com.thesis.serverfurnitureecommerce.domain.model.entity.OrderItemEntity;
import com.thesis.serverfurnitureecommerce.domain.model.entity.ProductEntity;
import com.thesis.serverfurnitureecommerce.domain.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.common.enums.StatusOrderEnum;
import com.thesis.serverfurnitureecommerce.domain.exception.AppException;
import com.thesis.serverfurnitureecommerce.domain.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.common.mapper.OrderMapper;
import com.thesis.serverfurnitureecommerce.common.utils.UserUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderServiceImpl implements OrderService {
    UserRepository userRepository;
    OrderRepository orderRepository;
    OrderItemRepository orderItemRepository;
    OrderMapper orderMapper;
    ProductRepository productRepository;

    @Override
    public void createOrder(OrderRequest orderRequest) {
        String username = UserUtil.getUsername();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        log.info("Creating order for user {}", username);
        OrderEntity orderEntity = orderMapper.convertToEntity(orderRequest);
        orderEntity.setUser(user);
        orderEntity.setStatus(StatusOrderEnum.PENDING);
        orderRepository.save(orderEntity);
        log.info("Order created successfully with ID {}", orderEntity.getId());
       if (orderRequest.getOrderItemRequests() != null){
              for (OrderItemRequest orderItemRequest : orderRequest.getOrderItemRequests()){
                OrderItemEntity orderItemEntity = OrderItemEntity.create();
                orderItemEntity.setOrder(orderEntity);
                ProductEntity productEntity = productRepository.findById(orderItemRequest.getProductId())
                        .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
                orderItemEntity.setProduct(productEntity);
                orderItemEntity.setQuantity(orderItemRequest.getQuantity());
                orderItemRepository.save(orderItemEntity);
                orderEntity.getOrderItems().add(orderItemEntity);
              }
              orderRepository.save(orderEntity);
       }
    }

    @Override
    public void updateOrder(Long orderID, int status) {

    }

}
