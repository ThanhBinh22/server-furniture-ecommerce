package com.thesis.serverfurnitureecommerce.internal.services.supplier;

import com.thesis.serverfurnitureecommerce.internal.repositories.SupplierRepository;
import com.thesis.serverfurnitureecommerce.model.dto.SupplierDTO;
import com.thesis.serverfurnitureecommerce.model.entity.SupplierEntity;
import com.thesis.serverfurnitureecommerce.pkg.mapper.SupplierMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class SupplierServiceImpl implements SupplierService {
    SupplierRepository supplierRepository;
    SupplierMapper supplierMapper;

    @Override
    public List<SupplierDTO> getAll() {
        List<SupplierEntity> supplierEntities = supplierRepository.findAll();
        List<SupplierDTO> supplierDTOS = supplierEntities.stream().map(supplierMapper::convertToDTO).toList();
        return supplierDTOS;
    }
}
