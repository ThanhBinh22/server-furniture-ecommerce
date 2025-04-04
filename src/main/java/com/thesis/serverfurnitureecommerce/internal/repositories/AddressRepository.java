package com.thesis.serverfurnitureecommerce.internal.repositories;

import com.thesis.serverfurnitureecommerce.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
}
