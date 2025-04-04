package com.thesis.serverfurnitureecommerce.application.services.support;

import com.thesis.serverfurnitureecommerce.presentation.requestv2.SupportCustomerRequest;
import com.thesis.serverfurnitureecommerce.infrastructure.persistence.SupportCustomerRepository;
import com.thesis.serverfurnitureecommerce.domain.model.entity.SupportCustomerEntity;
import com.thesis.serverfurnitureecommerce.common.mapper.SupportCustomerMapper;
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
    public void saveContact(SupportCustomerRequest supportCustomerRequest) {
        log.info("Service to save contact: {}", supportCustomerRequest);
        SupportCustomerEntity supportCustomer = supportCustomerMapper.toEntity(supportCustomerRequest);
        supportCustomer.setIsSolve(false);
        supportCustomerRepository.save(supportCustomer);
    }
}
