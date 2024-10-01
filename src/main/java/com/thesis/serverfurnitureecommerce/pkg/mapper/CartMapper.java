package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.model.dto.CartDTO;
import com.thesis.serverfurnitureecommerce.model.entity.CartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    @Mapping(source = "user.id", target = "user.id")
    @Mapping(target = "cartItems", source = "cartItems")
    CartDTO toDTO(CartEntity cartEntity);

    @Mapping(source = "user.id", target = "user.id")
    @Mapping(target = "cartItems", source = "cartItems")
    CartEntity toEntity(CartDTO cartDTO);
}
