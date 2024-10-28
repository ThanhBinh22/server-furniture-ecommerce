package com.thesis.serverfurnitureecommerce.internal.services.product;

import com.thesis.serverfurnitureecommerce.internal.repositories.IProductRepository;
import com.thesis.serverfurnitureecommerce.model.dto.ProductDTO;
import com.thesis.serverfurnitureecommerce.model.entity.ProductEntity;
import com.thesis.serverfurnitureecommerce.pkg.mapper.IProductMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements IProductService{
    IProductRepository productRepository;
    IProductMapper productMapper;


    @Override
    public ProductDTO findByProductID(int productID) {
        log.info("Invoke to service find product by id");
        ProductEntity productEntity = productRepository.findById(productID)
                .filter(product -> product.getIsActive() == 1)
                .orElse(null);
        return productMapper.convertToDTO(productEntity);
    }
}
