package com.thesis.serverfurnitureecommerce.internal.services.support;


import com.thesis.serverfurnitureecommerce.domain.requestv2.SupportCustomerRequest;

public interface SupportCustomerService {

    public void saveContact(SupportCustomerRequest supportCustomerDTO);

}
