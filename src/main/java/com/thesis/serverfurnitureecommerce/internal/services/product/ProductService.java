package com.thesis.serverfurnitureecommerce.internal.services.product;

import com.thesis.serverfurnitureecommerce.model.dto.ProductDTO;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<ProductDTO> findAll();

    List<ProductDTO> findByMultiFields(Map<String, Object> productSearchRequest);

    ProductDTO findByProductID(int productID);

    void saveToWishlist(Integer productID, String username);

    List<ProductDTO> getWishlist(String username);

    void deleteWishlist(Integer productID, String username);
}
