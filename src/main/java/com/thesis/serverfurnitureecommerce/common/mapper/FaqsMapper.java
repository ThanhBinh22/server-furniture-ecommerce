package com.thesis.serverfurnitureecommerce.common.mapper;

import com.thesis.serverfurnitureecommerce.domain.model.vo.FaqsVO;
import com.thesis.serverfurnitureecommerce.domain.model.entity.FaqsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FaqsMapper {

    FaqsEntity convertToEntity(FaqsVO faqsVO);

    FaqsVO convertToDTO(FaqsEntity faqsEntity);

}
