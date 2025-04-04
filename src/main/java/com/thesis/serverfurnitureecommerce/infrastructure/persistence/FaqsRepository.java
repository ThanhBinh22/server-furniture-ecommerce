package com.thesis.serverfurnitureecommerce.infrastructure.persistence;

import com.thesis.serverfurnitureecommerce.domain.model.entity.FaqsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqsRepository extends CrudRepository<FaqsEntity, Integer> {
}
