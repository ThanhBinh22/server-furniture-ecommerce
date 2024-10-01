package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.model.dto.UserDTO;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(UserEntity userEntity);

    UserEntity toEntity(UserDTO userDTO);

    List<UserDTO> toListDTO(List<UserEntity> userEntities);
}
