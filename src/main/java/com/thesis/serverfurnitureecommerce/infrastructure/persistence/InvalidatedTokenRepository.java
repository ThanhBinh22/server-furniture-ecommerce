package com.thesis.serverfurnitureecommerce.infrastructure.persistence;

import com.thesis.serverfurnitureecommerce.domain.model.entity.InvalidatedTokenEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedTokenRepository extends CrudRepository<InvalidatedTokenEntity, Integer> { }
