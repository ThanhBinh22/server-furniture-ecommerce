package com.thesis.serverfurnitureecommerce.internal.repositories;

import com.thesis.serverfurnitureecommerce.model.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}
