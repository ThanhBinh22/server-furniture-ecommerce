package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.model.dto.ReviewDTO;
import com.thesis.serverfurnitureecommerce.model.entity.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    ReviewDTO toDTO(ReviewEntity reviewEntity);

    ReviewEntity toEntity(ReviewDTO reviewDTO);
}
