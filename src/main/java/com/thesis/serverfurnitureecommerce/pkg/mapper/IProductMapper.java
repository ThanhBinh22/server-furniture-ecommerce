package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.model.dto.ProductDTO;
import com.thesis.serverfurnitureecommerce.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IImageMapper.class})
public interface IProductMapper {
    @Mapping(target = "images", source = "images")
    ProductEntity convertToEntity(ProductDTO productDTO);

    @Mapping(target = "images", source = "images")
    ProductDTO convertToDTO(ProductEntity productEntity);
}
