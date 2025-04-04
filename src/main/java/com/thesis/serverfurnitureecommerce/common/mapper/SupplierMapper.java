package com.thesis.serverfurnitureecommerce.common.mapper;

import com.thesis.serverfurnitureecommerce.domain.model.vo.SupplierVO;
import com.thesis.serverfurnitureecommerce.domain.model.entity.SupplierEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    SupplierEntity convertToEntity(SupplierVO supplierVO);

    SupplierVO convertToDTO(SupplierEntity supplierEntity);

}
