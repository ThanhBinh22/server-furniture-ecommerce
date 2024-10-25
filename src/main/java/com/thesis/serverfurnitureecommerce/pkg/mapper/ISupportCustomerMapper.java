package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.model.dto.SupportCustomerDTO;
import com.thesis.serverfurnitureecommerce.model.entity.SupportCustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISupportCustomerMapper {

    SupportCustomerEntity toEntity(SupportCustomerDTO supportCustomerDTO);

    SupportCustomerDTO toDTO(SupportCustomerEntity supportCustomerEntity);

}
