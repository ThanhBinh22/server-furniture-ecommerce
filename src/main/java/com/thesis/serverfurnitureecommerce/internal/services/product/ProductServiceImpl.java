package com.thesis.serverfurnitureecommerce.internal.services.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.serverfurnitureecommerce.domain.request.ProductSearchRequest;
import com.thesis.serverfurnitureecommerce.internal.repositories.*;
import com.thesis.serverfurnitureecommerce.internal.repositories.custom.product.IProductRepositoryCustom;
import com.thesis.serverfurnitureecommerce.model.dto.ImageDTO;
import com.thesis.serverfurnitureecommerce.model.dto.ProductDTO;
import com.thesis.serverfurnitureecommerce.model.dto.ReviewDTO;
import com.thesis.serverfurnitureecommerce.model.entity.ProductEntity;
import com.thesis.serverfurnitureecommerce.model.entity.ReviewEntity;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.model.entity.WishlistEntity;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.pkg.mapper.ImageMapper;
import com.thesis.serverfurnitureecommerce.pkg.mapper.ProductMapper;
import com.thesis.serverfurnitureecommerce.pkg.mapper.ReviewMapper;
import com.thesis.serverfurnitureecommerce.pkg.utils.CurrencyUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    IProductRepositoryCustom productRepositoryCustom;
    ProductMapper productMapper;
    ImageRepository imageRepository;
    ReviewRepository reviewRepository;
    ImageMapper imageMapper;
    ObjectMapper objectMapper;
    UserRepository userRepository;
    ReviewMapper reviewMapper;
    WishlistRepository wishlistRepository;


    @Override
    public List<ProductDTO> findAll() {
        log.info("Invoke to service find all product");
        List<ProductEntity> productEntities = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (ProductEntity product : productEntities) {
            String price = CurrencyUtils.formatCurrencyVND(product.getPrice());
            ProductDTO productDTO = productMapper.convertToDTO(product);
            productDTO.setPrice(price);
            productDTO.setImages(getImagesByProductID(productDTO.getId()));
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public List<ProductDTO> findByMultiFields(Map<String, Object> productSearchRequest) {
        ProductSearchRequest searchRequest = objectMapper.convertValue(productSearchRequest, ProductSearchRequest.class);
        List<ProductEntity> productEntities = productRepositoryCustom.findAllMultiField(searchRequest);
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
        List<ReviewDTO> reviewDTOS = getReviewByProductID(productID);
        ProductDTO productDTO = productMapper.convertToDTO(productEntity);
        String price = CurrencyUtils.formatCurrencyVND(productEntity.getPrice());
        productDTO.setPrice(price);
        productDTO.setImages(imageDTOS);
        productDTO.setReviewDTO(reviewDTOS);
        return productDTO;
    }

    @Override
    public void saveToWishlist(Integer productID, String email) {
        log.info("Invoke to service save product to wishlist");
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        ProductEntity product = productRepository.findById(productID).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        WishlistEntity wishlistEntity = WishlistEntity.create();
        wishlistEntity.setProduct(product);
        wishlistEntity.setUser(user);
        wishlistRepository.save(wishlistEntity);
    }


    private List<ImageDTO> getImagesByProductID(Integer productID) {
        return imageRepository.getImagesByProductID(productID)
                .stream()
                .map(imageMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    private List<ReviewDTO> getReviewByProductID(Integer productID) {
        List<ReviewEntity> reviewEntities = reviewRepository.getReviewByProductID(productID);
        List<ReviewDTO> reviews = new ArrayList<>();
        for (ReviewEntity review : reviewEntities) {
            UserEntity user = userRepository.findById(review.getUser().getId()).orElse(null);
            ReviewDTO reviewDTO = reviewMapper.convertToDTO(review);
            reviewDTO.setUsername(user.getUsername());
            reviews.add(reviewDTO);
        }
        return reviews;
    }


}
