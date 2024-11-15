package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.model.dto.ImageDTO;
import com.thesis.serverfurnitureecommerce.model.entity.ImageEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    ImageDTO convertToDTO(ImageEntity imageEntity);

    ImageEntity convertToEntity(ImageDTO imageDTO);

    List<ImageDTO> convertToDTOList(List<ImageEntity> imageEntity);
}
