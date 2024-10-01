package com.thesis.serverfurnitureecommerce.pkg.mapper;


import com.thesis.serverfurnitureecommerce.model.dto.CartItemDTO;
import com.thesis.serverfurnitureecommerce.model.entity.CartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);

    CartItemDTO toDTO(CartItemEntity cartItemEntity);

    CartItemEntity toEntity(CartItemDTO cartItemDTO);
}
