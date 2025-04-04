package com.thesis.serverfurnitureecommerce.infrastructure.persistence;

import com.thesis.serverfurnitureecommerce.domain.model.entity.ImageEntity;
import com.thesis.serverfurnitureecommerce.domain.model.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, Integer> {
    List<ImageEntity> getImagesByProduct(ProductEntity product);
}
