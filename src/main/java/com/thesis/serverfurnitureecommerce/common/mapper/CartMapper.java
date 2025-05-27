package com.thesis.serverfurnitureecommerce.common.mapper;

import com.thesis.serverfurnitureecommerce.presentation.requestv2.CartRequest;
import com.thesis.serverfurnitureecommerce.domain.model.entity.CartItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartItemEntity converToEntity(CartRequest cartRequest);

}
