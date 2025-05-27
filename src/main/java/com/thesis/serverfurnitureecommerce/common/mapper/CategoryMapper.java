package com.thesis.serverfurnitureecommerce.common.mapper;

import com.thesis.serverfurnitureecommerce.domain.model.vo.CategoryVO;
import com.thesis.serverfurnitureecommerce.domain.model.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryEntity convertToEntity(CategoryVO categoryVO);

    CategoryVO convertToDTO(CategoryEntity categoryEntity);

}
