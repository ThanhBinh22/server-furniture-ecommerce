package com.thesis.serverfurnitureecommerce.pkg.mapper;

import com.thesis.serverfurnitureecommerce.model.dto.AddressDTO;
import com.thesis.serverfurnitureecommerce.model.entity.AddressEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {
    AddressDTO convertToEntity(AddressEntity address);

    AddressEntity convertToDTO(AddressDTO addressDTO);
}
