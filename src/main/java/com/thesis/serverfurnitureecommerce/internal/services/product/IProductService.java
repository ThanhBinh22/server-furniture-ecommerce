package com.thesis.serverfurnitureecommerce.internal.services.product;

import com.thesis.serverfurnitureecommerce.domain.request.ProductSearchRequest;
import com.thesis.serverfurnitureecommerce.model.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findAll();

//    List<ProductDTO> findByName(String name);

    List<ProductDTO> findByMultiFields(ProductSearchRequest productSearchRequest);

    ProductDTO findByProductID(int productID);
}
