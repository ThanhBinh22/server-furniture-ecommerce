package com.thesis.serverfurnitureecommerce.common.mapper;

import com.thesis.serverfurnitureecommerce.presentation.requestv2.SupportCustomerRequest;
import com.thesis.serverfurnitureecommerce.domain.model.vo.SupportCustomerVO;
import com.thesis.serverfurnitureecommerce.domain.model.entity.SupportCustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupportCustomerMapper {

    SupportCustomerEntity toEntity(SupportCustomerRequest supportCustomerDTO);

    SupportCustomerVO toDTO(SupportCustomerEntity supportCustomerEntity);

}
