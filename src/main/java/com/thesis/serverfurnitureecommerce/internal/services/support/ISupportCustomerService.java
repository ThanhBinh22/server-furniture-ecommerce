package com.thesis.serverfurnitureecommerce.internal.services.support;

import com.thesis.serverfurnitureecommerce.model.dto.SupportCustomerDTO;
import org.springframework.stereotype.Service;


public interface ISupportCustomerService {

    public void saveContact(SupportCustomerDTO supportCustomerDTO);

}
