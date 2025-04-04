package com.thesis.serverfurnitureecommerce.common.mapper;

import com.thesis.serverfurnitureecommerce.domain.model.vo.UserVO;
import com.thesis.serverfurnitureecommerce.presentation.requestv2.RegisterRequest;
import com.thesis.serverfurnitureecommerce.presentation.request.UpdateAccountRequest;
import com.thesis.serverfurnitureecommerce.domain.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserVO toDTO(UserEntity userEntity);
    UserEntity toEntity(UserVO userVO);
    UserEntity fromRequestToEntity(RegisterRequest registerRequest);
    UserEntity fromUpdateToEntity(UpdateAccountRequest updateAccountRequest);
    List<UserVO> toListDTO(List<UserEntity> userEntities);
}
