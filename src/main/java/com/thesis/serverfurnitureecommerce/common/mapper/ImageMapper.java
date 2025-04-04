package com.thesis.serverfurnitureecommerce.common.mapper;

import com.thesis.serverfurnitureecommerce.domain.model.entity.ImageEntity;
import com.thesis.serverfurnitureecommerce.domain.model.vo.ImageVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    ImageVO convertToDTO(ImageEntity imageEntity);

    ImageEntity convertToEntity(ImageVO imageVO);

    List<ImageVO> convertToDTOList(List<ImageEntity> imageEntity);
}
