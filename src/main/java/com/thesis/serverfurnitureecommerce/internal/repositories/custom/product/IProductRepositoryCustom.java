package com.thesis.serverfurnitureecommerce.internal.repositories.custom.product;

import com.thesis.serverfurnitureecommerce.domain.request.ProductSearchRequest;
import com.thesis.serverfurnitureecommerce.model.entity.ProductEntity;

import java.util.List;

public interface IProductRepositoryCustom {
    List<ProductEntity> findAllMultiField(ProductSearchRequest productSearchRequest);
}
