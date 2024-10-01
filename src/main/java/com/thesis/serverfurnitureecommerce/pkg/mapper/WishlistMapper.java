package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.model.dto.WishlistDTO;
import com.thesis.serverfurnitureecommerce.model.entity.WishlistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WishlistMapper {

    WishlistMapper INSTANCE = Mappers.getMapper(WishlistMapper.class);

    WishlistDTO toDTO(WishlistEntity wishlistEntity);

    WishlistEntity toEntity(WishlistDTO wishlistDTO);
}
