package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.model.dto.UserLogDTO;
import com.thesis.serverfurnitureecommerce.model.entity.UserLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface UserLogMapper {
    UserLogMapper INSTANCE = Mappers.getMapper(UserLogMapper.class);

    UserLogDTO toDTO(UserLogEntity userLogEntity);

    UserLogEntity toEntity(UserLogDTO userLogDTO);
}
