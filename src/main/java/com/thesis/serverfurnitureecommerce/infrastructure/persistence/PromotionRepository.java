package com.thesis.serverfurnitureecommerce.infrastructure.persistence;

import com.thesis.serverfurnitureecommerce.domain.model.entity.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<PromotionEntity, Integer> {
}
