package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.model.dto.SupplierDTO;
import com.thesis.serverfurnitureecommerce.model.entity.SupplierEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISupplierMapper {

    SupplierEntity convertToEntity(SupplierDTO supplierDTO);

    SupplierDTO convertToDTO(SupplierEntity supplierEntity);

}
