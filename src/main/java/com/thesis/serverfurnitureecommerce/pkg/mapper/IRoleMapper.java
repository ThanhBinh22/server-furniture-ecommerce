package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.model.dto.RoleDTO;
import com.thesis.serverfurnitureecommerce.model.entity.RoleEntity;
import org.mapstruct.Mapper;

/**
 * Mapper interface dùng để chuyển đổi giữa RoleEntity và RoleDTO
 * Nó sẽ dựa bào thư viện mapstruct để tự động convert qua lại giữa
 * hai đối rượng RoleEntity và RoleDTO với nhau
 */
@Mapper(componentModel = "spring")
public interface IRoleMapper {
    /**
     * Chuyển đổi RoleEntity sang RoleDTO
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
