package com.thesis.serverfurnitureecommerce.domain.repository;

import com.thesis.serverfurnitureecommerce.domain.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
