package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.model.dto.FaqsDTO;
import com.thesis.serverfurnitureecommerce.model.entity.FaqsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FaqsMapper {

    FaqsEntity convertToEntity(FaqsDTO faqsDTO);

    FaqsDTO convertToDTO(FaqsEntity faqsEntity);

}
