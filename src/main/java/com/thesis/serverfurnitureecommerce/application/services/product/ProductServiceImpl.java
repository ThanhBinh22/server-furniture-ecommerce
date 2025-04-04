package com.thesis.serverfurnitureecommerce.application.services.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.serverfurnitureecommerce.domain.model.entity.*;
import com.thesis.serverfurnitureecommerce.domain.model.vo.ImageVO;
import com.thesis.serverfurnitureecommerce.domain.model.vo.ProductVO;
import com.thesis.serverfurnitureecommerce.domain.model.vo.ReviewVO;
import com.thesis.serverfurnitureecommerce.infrastructure.persistence.*;
import com.thesis.serverfurnitureecommerce.presentation.requestv2.ProductRequest;
import com.thesis.serverfurnitureecommerce.presentation.request.ProductSearchRequest;
import com.thesis.serverfurnitureecommerce.infrastructure.persistence.custom.product.IProductRepositoryCustom;
import com.thesis.serverfurnitureecommerce.domain.exception.AppException;
import com.thesis.serverfurnitureecommerce.domain.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.common.mapper.ImageMapper;
import com.thesis.serverfurnitureecommerce.common.mapper.ProductMapper;
import com.thesis.serverfurnitureecommerce.common.mapper.ReviewMapper;
import com.thesis.serverfurnitureecommerce.common.utils.CurrencyUtils;
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
    CategoryRepository categoryRepository;
    SupplierRepository supplierRepository;

    @Override
    public List<ProductVO> findAll() {
        log.info("Invoke to service find all product");
        List<ProductEntity> productEntities = productRepository.findAllByIsActive(1);
        log.info("Found {} products", productEntities.size());
        List<ProductVO> productVOS = new ArrayList<>();
        for (ProductEntity product : productEntities) {
            String price = CurrencyUtils.formatCurrencyVND(product.getPrice());
            ProductVO productVO = productMapper.convertToDTO(product);
            productVO.setPrice(price);
            productVO.setImages(getImagesByProductID(productVO.getId()));
            productVOS.add(productVO);
        }
        return productVOS;
    }

    @Override
    public List<ProductVO> findByMultiFields(Map<String, Object> productSearchRequest) {
        ProductSearchRequest searchRequest = objectMapper.convertValue(productSearchRequest, ProductSearchRequest.class);
        List<ProductEntity> productEntities = productRepositoryCustom.findAllMultiField(searchRequest);
        List<ProductVO> productVOS = productEntities.stream()
                .map(productMapper::convertToDTO)
                .collect(Collectors.toList());
        productVOS.forEach(productDTO -> productDTO.setImages(getImagesByProductID(productDTO.getId())));
        return productVOS;
    }

    @Override
    public ProductVO findByProductID(int productID) {
        log.info("Invoke to service find product by id");
        ProductEntity productEntity = productRepository.findById(productID)
                .filter(product -> product.getIsActive() == 1)
                .orElse(null);
        List<ImageVO> imageVOS = getImagesByProductID(productID);
        List<ReviewVO> reviewVOS = getReviewByProductID(productID);
        ProductVO productVO = productMapper.convertToDTO(productEntity);
        assert productEntity != null;
        String price = CurrencyUtils.formatCurrencyVND(productEntity.getPrice());
        productVO.setPrice(price);
        productVO.setImages(imageVOS);
        productVO.setReviewVO(reviewVOS);
        return productVO;
    }

    @Override
    public void saveToWishlist(Integer productID, String username) {
        log.info("Invoke to service save product to wishlist");
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        ProductEntity product = productRepository.findById(productID).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        WishlistEntity wishlistEntity = WishlistEntity.create();
        wishlistEntity.setProduct(product);
        wishlistEntity.setUser(user);
        wishlistRepository.save(wishlistEntity);
    }

    @Override
    public List<ProductVO> getWishlist(String username) {
        log.info("Invoke to service get wishlist");
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        List<WishlistEntity> wishlistEntities = wishlistRepository.findByUser(user);
        List<ProductVO> productVOS = new ArrayList<>();
        for (WishlistEntity wishlist : wishlistEntities) {
            ProductEntity product = productRepository.findById(wishlist.getProduct().getId()).orElse(null);
            ProductVO productVO = productMapper.convertToDTO(product);
            assert product != null;
            String price = CurrencyUtils.formatCurrencyVND(product.getPrice());
            productVO.setPrice(price);
            productVO.setImages(getImagesByProductID(productVO.getId()));
            productVOS.add(productVO);
        }
        return productVOS;
    }

    @Override
    public void deleteWishlist(Integer productID, String username) {
        log.info("Invoke to service delete product from wishlist");
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        ProductEntity product = productRepository.findById(productID).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        WishlistEntity wishlistEntity = wishlistRepository.findByUserAndProduct(user, product);
        wishlistRepository.delete(wishlistEntity);
    }

    @Override
    public ProductVO updateProduct(ProductRequest product) {
        ProductEntity productEntity = productRepository.findById(product.id())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productEntity.setName(product.name());
        productEntity.setPrice(product.price());
        productEntity.setDescription(product.description());
        if (product.stock() != null) {
            productEntity.setStock(product.stock());
        }
        if (product.categoryID() != null) {
            CategoryEntity categoryEntity = categoryRepository.findById(product.categoryID())
                    .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
            productEntity.setCategory(categoryEntity);
        }
        if (product.supplierID() != null) {
            SupplierEntity supplierEntity = supplierRepository.findById(product.supplierID())
                    .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
            productEntity.setSupplier(supplierEntity);
        }
        productRepository.save(productEntity);

        return productMapper.convertToDTO(productEntity);
    }

    @Override
    public void deleteProduct(Integer id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        product.setIsActive((short) 0);
        productRepository.save(product);
    }

    @Override
    public void addProduct(ProductRequest productRequest) {
        // Tạo và thiết lập ProductEntity
        ProductEntity productEntity = ProductEntity.createNewProductEntity();
        CategoryEntity categoryEntity = categoryRepository.findById(productRequest.categoryID())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        SupplierEntity supplierEntity = supplierRepository.findById(productRequest.supplierID())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        productEntity.setCategory(categoryEntity);
        productEntity.setSupplier(supplierEntity);
        productEntity.setName(productRequest.name());
        productEntity.setPrice(productRequest.price());
        productEntity.setDescription(productRequest.description());
        productEntity.setStock(productRequest.stock());

        // Bước 1: Lưu ProductEntity trước để đảm bảo id được tạo
        ProductEntity savedProduct = productRepository.save(productEntity);
        log.info("Sản phẩm vừa thêm: {}", savedProduct.getId());

        // Bước 2: Thêm ImageEntity sau khi productEntity đã có id
        if (productRequest.image() != null) {
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setImageUrl(productRequest.image());
            imageEntity.setProduct(savedProduct); // Gán ProductEntity cho ImageEntity
            savedProduct.getImages().add(imageEntity); // Thêm vào danh sách ảnh
        }

        // Bước 3: Lưu lại ProductEntity (Hibernate sẽ cascade ImageEntity)
        productRepository.save(savedProduct);
    }



    private List<ImageVO> getImagesByProductID(Integer productID) {
        ProductEntity product = productRepository.findById(productID).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        return imageRepository.getImagesByProduct(product)
                .stream()
                .map(imageMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    private List<ReviewVO> getReviewByProductID(Integer productID) {
        List<ReviewEntity> reviewEntities = reviewRepository.getReviewByProductID(productID);
        List<ReviewVO> reviews = new ArrayList<>();
        for (ReviewEntity review : reviewEntities) {
            UserEntity user = userRepository.findById(review.getUser().getId()).orElse(null);
            ReviewVO reviewVO = reviewMapper.convertToDTO(review);
            assert user != null;
            reviewVO.setUsername(user.getUsername());
            reviews.add(reviewVO);
        }
        return reviews;
    }
}
