package com.thesis.serverfurnitureecommerce.internal.repositories;

import com.thesis.serverfurnitureecommerce.model.entity.FaqsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFaqsRepository extends CrudRepository<FaqsEntity, Integer> {
}
