package com.thesis.serverfurnitureecommerce.infrastructure.persistence;

import com.thesis.serverfurnitureecommerce.domain.model.entity.CartEntity;
import com.thesis.serverfurnitureecommerce.domain.model.entity.CartItemEntity;
import com.thesis.serverfurnitureecommerce.domain.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends CrudRepository<CartItemEntity, String> {

    @Query("SELECT c FROM CartItemEntity c WHERE c.cart = ?1 AND c.product = ?2")
    Optional<CartItemEntity> findByCartAndProduct(CartEntity cart, ProductEntity product);

    @Query("SELECT c FROM CartItemEntity c WHERE c.cart = ?1")
    List<CartItemEntity> findByCart(CartEntity cart);
}
