package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.model.dto.ProductDTO;
import com.thesis.serverfurnitureecommerce.model.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    ProductEntity convertToEntity(ProductDTO productDTO);

    ProductDTO convertToDTO(ProductEntity productEntity);
}
