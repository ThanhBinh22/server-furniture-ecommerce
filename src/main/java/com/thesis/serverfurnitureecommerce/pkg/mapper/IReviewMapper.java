package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.domain.request.ReviewRequest;
import com.thesis.serverfurnitureecommerce.model.dto.ReviewDTO;
import com.thesis.serverfurnitureecommerce.model.entity.ReviewEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IReviewMapper {
    ReviewDTO convertToDTO(ReviewEntity reviewEntity);

    ReviewEntity convertToEntity(ReviewDTO reviewDTO);

    ReviewEntity convertEntityFromRequest(ReviewRequest reviewRequest);
}
