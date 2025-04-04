package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.domain.requestv2.RegisterRequest;
import com.thesis.serverfurnitureecommerce.domain.request.UpdateAccountRequest;
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
    UserEntity fromRequestToEntity(RegisterRequest registerRequest);
    UserEntity fromUpdateToEntity(UpdateAccountRequest updateAccountRequest);
    List<UserDTO> toListDTO(List<UserEntity> userEntities);
}
