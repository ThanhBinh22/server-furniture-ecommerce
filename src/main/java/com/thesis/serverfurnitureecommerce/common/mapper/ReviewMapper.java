package com.thesis.serverfurnitureecommerce.common.mapper;

import com.thesis.serverfurnitureecommerce.domain.model.vo.ReviewVO;
import com.thesis.serverfurnitureecommerce.presentation.requestv2.ReviewRequest;
import com.thesis.serverfurnitureecommerce.domain.model.entity.ReviewEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewVO convertToDTO(ReviewEntity reviewEntity);

    ReviewEntity convertToEntity(ReviewVO reviewVO);

    ReviewEntity convertEntityFromRequest(ReviewRequest reviewRequest);
}
