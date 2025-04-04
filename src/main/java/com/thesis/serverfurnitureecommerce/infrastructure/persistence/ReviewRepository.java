package com.thesis.serverfurnitureecommerce.infrastructure.persistence;

import com.thesis.serverfurnitureecommerce.domain.model.entity.ProductEntity;
import com.thesis.serverfurnitureecommerce.domain.model.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    @Query("SELECT r FROM ReviewEntity r WHERE r.product.id = ?1")
    List<ReviewEntity> getReviewByProductID(Integer productID);

    List<ReviewEntity> findByProduct(ProductEntity product);
}
