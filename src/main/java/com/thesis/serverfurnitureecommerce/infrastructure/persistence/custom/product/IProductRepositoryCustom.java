package com.thesis.serverfurnitureecommerce.infrastructure.persistence.custom.product;

import com.thesis.serverfurnitureecommerce.presentation.request.ProductSearchRequest;
import com.thesis.serverfurnitureecommerce.domain.model.entity.ProductEntity;

import java.util.List;

public interface IProductRepositoryCustom {
    List<ProductEntity> findAllMultiField(ProductSearchRequest productSearchRequest);
}
