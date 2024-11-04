package com.thesis.serverfurnitureecommerce.internal.services.product;

import com.thesis.serverfurnitureecommerce.domain.request.ProductSearchRequest;
import com.thesis.serverfurnitureecommerce.internal.repositories.IImageRepository;
import com.thesis.serverfurnitureecommerce.internal.repositories.IProductRepository;
import com.thesis.serverfurnitureecommerce.internal.repositories.custom.product.IProductRepositoryCustom;
import com.thesis.serverfurnitureecommerce.model.dto.ImageDTO;
import com.thesis.serverfurnitureecommerce.model.dto.ProductDTO;
import com.thesis.serverfurnitureecommerce.model.entity.ProductEntity;
import com.thesis.serverfurnitureecommerce.pkg.mapper.IImageMapper;
import com.thesis.serverfurnitureecommerce.pkg.mapper.IProductMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements IProductService {
    IProductRepository productRepository;
    IProductRepositoryCustom productRepositoryCustom;
    IProductMapper productMapper;
    IImageRepository iImageRepository;
    IImageMapper imageMapper;


    @Override
    public List<ProductDTO> findAll() {
        log.info("Invoke to service find all product");
        List<ProductEntity> productEntities = productRepository.findAll();
        List<ProductDTO> productDTOS = productEntities.stream()
                .map(productMapper::convertToDTO)
                .collect(Collectors.toList());
        productDTOS.forEach(productDTO -> productDTO.setImages(getImagesByProductID(productDTO.getId())));
        return productDTOS;
    }


    @Override
    public List<ProductDTO> findByMultiFields(ProductSearchRequest productSearchRequest) {
        log.info("Invoke to service find product by multi fields");
        List<ProductEntity> productEntities = productRepositoryCustom.findAllMultiField(productSearchRequest);
        List<ProductDTO> productDTOS = productEntities.stream()
                .map(productMapper::convertToDTO)
                .collect(Collectors.toList());
        productDTOS.forEach(productDTO -> productDTO.setImages(getImagesByProductID(productDTO.getId())));
        return productDTOS;
    }

    @Override
    public ProductDTO findByProductID(int productID) {
        log.info("Invoke to service find product by id");
        ProductEntity productEntity = productRepository.findById(productID)
                .filter(product -> product.getIsActive() == 1)
                .orElse(null);
        List<ImageDTO> imageDTOS = getImagesByProductID(productID);
        ProductDTO productDTO = productMapper.convertToDTO(productEntity);
        productDTO.setImages(imageDTOS);
        return productDTO;
    }


    private List<ImageDTO> getImagesByProductID(Integer productID) {
        return iImageRepository.getImagesByProductID(productID)
                .stream()
                .map(imageMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
