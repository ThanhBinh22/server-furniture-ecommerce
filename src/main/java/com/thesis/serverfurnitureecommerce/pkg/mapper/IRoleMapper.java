package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.model.dto.RoleDTO;
import com.thesis.serverfurnitureecommerce.model.entity.RoleEntity;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between RoleEntity and RoleDTO.
 * This interface provides methods to convert RoleEntity objects to RoleDTO
 * and vice versa.
 */
@Mapper(componentModel = "spring")
public interface IRoleMapper {

    /**
     * Converts a RoleEntity to a RoleDTO.
     *
     * @param roleEntity the RoleEntity to convert
     * @return the converted RoleDTO
     */
    RoleDTO toDTO(RoleEntity roleEntity);

    /**
     * Converts a RoleDTO to a RoleEntity.
     *
     * @param roleDTO the RoleDTO to convert
     * @return the converted RoleEntity
     */
    RoleEntity toEntity(RoleDTO roleDTO);
}
