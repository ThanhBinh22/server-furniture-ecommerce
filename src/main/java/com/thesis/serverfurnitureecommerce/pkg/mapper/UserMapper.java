package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.domain.request.RegisterRequest;
import com.thesis.serverfurnitureecommerce.domain.request.UpdateAccountRequest;
import com.thesis.serverfurnitureecommerce.model.dto.UserDTO;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Mapper interface for converting between UserEntity, UserDTO, and RegisterRequest.
 * This interface provides methods to convert UserEntity objects to UserDTOs,
 * convert RegisterRequest to UserEntity, and vice versa.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * Converts a UserEntity to a UserDTO.
     *
     * @param userEntity the UserEntity to convert
     * @return the converted UserDTO
     */
    UserDTO toDTO(UserEntity userEntity);

    /**
     * Converts a UserDTO to a UserEntity.
     *
     * @param userDTO the UserDTO to convert
     * @return the converted UserEntity
     */
    UserEntity toEntity(UserDTO userDTO);

    /**
     * Converts a RegisterRequest to a UserEntity.
     *
     * @param registerRequest the RegisterRequest to convert
     * @return the converted UserEntity
     */
    UserEntity fromRequestToEntity(RegisterRequest registerRequest);

    UserEntity fromUpdateToEntity(UpdateAccountRequest updateAccountRequest);
    /**
     * Converts a list of UserEntity objects to a list of UserDTOs.
     *
     * @param userEntities the list of UserEntity objects to convert
     * @return the list of converted UserDTOs
     */
    List<UserDTO> toListDTO(List<UserEntity> userEntities);
}
