package com.thesis.serverfurnitureecommerce.infrastructure.persistence;

import com.thesis.serverfurnitureecommerce.domain.model.entity.SupportCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportCustomerRepository extends JpaRepository<SupportCustomerEntity, Integer> {
}
