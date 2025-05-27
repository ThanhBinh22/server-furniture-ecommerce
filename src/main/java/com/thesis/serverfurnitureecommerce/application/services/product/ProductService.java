package com.thesis.serverfurnitureecommerce.application.services.product;

import com.thesis.serverfurnitureecommerce.domain.model.vo.ProductVO;
import com.thesis.serverfurnitureecommerce.presentation.requestv2.ProductRequest;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<ProductVO> findAll();

    List<ProductVO> findByMultiFields(Map<String, Object> productSearchRequest);

    ProductVO findByProductID(int productID);

    void saveToWishlist(Integer productID, String username);

    List<ProductVO> getWishlist(String username);

    void deleteWishlist(Integer productID, String username);

    ProductVO updateProduct(ProductRequest product);

    void deleteProduct(Integer id);

    void addProduct(ProductRequest productRequest);
}
