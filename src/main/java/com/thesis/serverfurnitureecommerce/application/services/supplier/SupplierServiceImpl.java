package com.thesis.serverfurnitureecommerce.application.services.supplier;

import com.thesis.serverfurnitureecommerce.domain.model.vo.SupplierVO;
import com.thesis.serverfurnitureecommerce.infrastructure.persistence.SupplierRepository;
import com.thesis.serverfurnitureecommerce.domain.model.entity.SupplierEntity;
import com.thesis.serverfurnitureecommerce.common.mapper.SupplierMapper;
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
    public List<SupplierVO> getAll() {
        List<SupplierEntity> supplierEntities = supplierRepository.findAll();
        List<SupplierVO> supplierVOS = supplierEntities.stream().map(supplierMapper::convertToDTO).toList();
        return supplierVOS;
    }
}
