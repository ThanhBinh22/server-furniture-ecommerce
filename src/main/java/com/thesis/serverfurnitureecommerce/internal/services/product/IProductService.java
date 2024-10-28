package com.thesis.serverfurnitureecommerce.internal.services.product;

import com.thesis.serverfurnitureecommerce.model.dto.ProductDTO;

public interface IProductService {
    ProductDTO findByProductID(int productID);
}
