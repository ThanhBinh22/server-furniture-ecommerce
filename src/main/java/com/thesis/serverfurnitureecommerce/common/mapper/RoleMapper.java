package com.thesis.serverfurnitureecommerce.common.mapper;

import com.thesis.serverfurnitureecommerce.domain.model.vo.RoleVO;
import com.thesis.serverfurnitureecommerce.domain.model.entity.RoleEntity;
import org.mapstruct.Mapper;

/**
 * Mapper interface dùng để chuyển đổi giữa RoleEntity và RoleDTO
 * Nó sẽ dựa bào thư viện mapstruct để tự động convert qua lại giữa
 * hai đối rượng RoleEntity và RoleDTO với nhau
 */
@Mapper(componentModel = "spring")
public interface RoleMapper {
    /**
     * Chuyển đổi RoleEntity sang RoleDTO
     *
     * @param roleEntity the RoleEntity to convert
     * @return the converted RoleDTO
     */
    RoleVO toDTO(RoleEntity roleEntity);
    /**
     * Converts a RoleDTO to a RoleEntity.
     *
     * @param roleVO the RoleDTO to convert
     * @return the converted RoleEntity
     */
    RoleEntity toEntity(RoleVO roleVO);
}
