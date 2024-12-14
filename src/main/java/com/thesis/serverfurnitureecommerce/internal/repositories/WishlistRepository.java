package com.thesis.serverfurnitureecommerce.internal.repositories;

import com.thesis.serverfurnitureecommerce.model.entity.ProductEntity;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.model.entity.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishlistRepository extends JpaRepository<WishlistEntity, Long> {
    @Query("SELECT w FROM WishlistEntity w WHERE w.user = ?1")
    List<WishlistEntity> findByUser(UserEntity user);

    @Query("SELECT w FROM WishlistEntity w WHERE w.user = ?1 AND w.product = ?2")
    WishlistEntity findByUserAndProduct(UserEntity user, ProductEntity product);
}
