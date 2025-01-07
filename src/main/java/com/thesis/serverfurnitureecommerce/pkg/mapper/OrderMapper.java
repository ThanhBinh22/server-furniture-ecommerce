package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.domain.request.OrderRequest;
import com.thesis.serverfurnitureecommerce.internal.repositories.OrderItemRepository;
import com.thesis.serverfurnitureecommerce.model.dto.OrderItemDTO;
import com.thesis.serverfurnitureecommerce.model.entity.OrderEntity;
import com.thesis.serverfurnitureecommerce.model.entity.OrderItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderEntity convertToEntity(OrderRequest orderRequest);

    OrderItemEntity convertOrderItemToEntity(OrderItemDTO orderItemDTO);

}
