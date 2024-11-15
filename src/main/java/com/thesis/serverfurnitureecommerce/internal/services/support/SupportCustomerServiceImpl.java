package com.thesis.serverfurnitureecommerce.internal.services.support;

import com.thesis.serverfurnitureecommerce.internal.repositories.SupportCustomerRepository;
import com.thesis.serverfurnitureecommerce.model.dto.SupportCustomerDTO;
import com.thesis.serverfurnitureecommerce.model.entity.SupportCustomerEntity;
import com.thesis.serverfurnitureecommerce.pkg.mapper.SupportCustomerMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SupportCustomerServiceImpl implements SupportCustomerService {
    SupportCustomerRepository supportCustomerRepository;
    SupportCustomerMapper supportCustomerMapper;

    @Override
    public void saveContact(SupportCustomerDTO supportCustomerDTO) {
        log.info("Service to save contact: {}", supportCustomerDTO);
        SupportCustomerEntity supportCustomer = supportCustomerMapper.toEntity(supportCustomerDTO);
        supportCustomer.setIsSolve(false);
        supportCustomerRepository.save(supportCustomer);
    }
}
