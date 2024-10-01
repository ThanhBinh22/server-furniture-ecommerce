package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.model.dto.OrderItemDTO;
import com.thesis.serverfurnitureecommerce.model.entity.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItemDTO toDTO(OrderItemEntity orderItemEntity);

    OrderItemEntity toEntity(OrderItemDTO orderItemDTO);
}
