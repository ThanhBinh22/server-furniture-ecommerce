package com.thesis.serverfurnitureecommerce.pkg.mapper;



import com.thesis.serverfurnitureecommerce.model.dto.CategoryDTO;
import com.thesis.serverfurnitureecommerce.model.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDTO(CategoryEntity categoryEntity);

    CategoryEntity toEntity(CategoryDTO categoryDTO);
}
