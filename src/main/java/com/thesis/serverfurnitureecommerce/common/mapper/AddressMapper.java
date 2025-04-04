package com.thesis.serverfurnitureecommerce.common.mapper;

import com.thesis.serverfurnitureecommerce.domain.model.vo.AddressVO;
import com.thesis.serverfurnitureecommerce.domain.model.entity.AddressEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {
    AddressVO convertToEntity(AddressEntity address);

    AddressEntity convertToDTO(AddressVO addressVO);
}
