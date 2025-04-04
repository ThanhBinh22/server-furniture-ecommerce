package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.domain.requestv2.ReviewRequest;
import com.thesis.serverfurnitureecommerce.model.dto.ReviewDTO;
import com.thesis.serverfurnitureecommerce.model.entity.ReviewEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDTO convertToDTO(ReviewEntity reviewEntity);

    ReviewEntity convertToEntity(ReviewDTO reviewDTO);

    ReviewEntity convertEntityFromRequest(ReviewRequest reviewRequest);
}
