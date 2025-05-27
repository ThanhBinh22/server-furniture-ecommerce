package com.thesis.serverfurnitureecommerce.domain.repository;

import com.thesis.serverfurnitureecommerce.domain.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
}
