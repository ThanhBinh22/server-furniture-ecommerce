package com.thesis.serverfurnitureecommerce.pkg.mapper;


import com.thesis.serverfurnitureecommerce.model.dto.RoleDTO;
import com.thesis.serverfurnitureecommerce.model.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleMapper {

    RoleDTO toDTO(RoleEntity roleEntity);

    RoleEntity toEntity(RoleDTO roleDTO);
}
