package com.thesis.serverfurnitureecommerce.internal.repositories;

import com.thesis.serverfurnitureecommerce.model.entity.CartEntity;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<CartEntity, Long> {

        CartEntity findByUser(UserEntity user);
}
