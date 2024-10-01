package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.model.dto.SupplierDTO;
import com.thesis.serverfurnitureecommerce.model.entity.SupplierEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    SupplierDTO toDTO(SupplierEntity supplierEntity);

    SupplierEntity toEntity(SupplierDTO supplierDTO);
}
