package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.domain.requestv2.SupportCustomerRequest;
import com.thesis.serverfurnitureecommerce.model.dto.SupportCustomerDTO;
import com.thesis.serverfurnitureecommerce.model.entity.SupportCustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupportCustomerMapper {

    SupportCustomerEntity toEntity(SupportCustomerRequest supportCustomerDTO);

    SupportCustomerDTO toDTO(SupportCustomerEntity supportCustomerEntity);

}
