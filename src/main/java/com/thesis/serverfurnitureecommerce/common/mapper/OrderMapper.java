package com.thesis.serverfurnitureecommerce.common.mapper;

import com.thesis.serverfurnitureecommerce.presentation.request.OrderRequest;
import com.thesis.serverfurnitureecommerce.domain.model.vo.OrderItemVO;
import com.thesis.serverfurnitureecommerce.domain.model.entity.OrderEntity;
import com.thesis.serverfurnitureecommerce.domain.model.entity.OrderItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderEntity convertToEntity(OrderRequest orderRequest);

    OrderItemEntity convertOrderItemToEntity(OrderItemVO orderItemVO);

}
