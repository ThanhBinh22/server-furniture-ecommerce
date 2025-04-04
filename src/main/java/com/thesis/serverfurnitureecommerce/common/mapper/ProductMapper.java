package com.thesis.serverfurnitureecommerce.common.mapper;

import com.thesis.serverfurnitureecommerce.domain.model.vo.ProductVO;
import com.thesis.serverfurnitureecommerce.domain.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ImageMapper.class})
public interface ProductMapper {
    @Mapping(target = "images", source = "images")
    ProductEntity convertToEntity(ProductVO productVO);

    @Mapping(target = "images", source = "images")
    ProductVO convertToDTO(ProductEntity productEntity);
}
