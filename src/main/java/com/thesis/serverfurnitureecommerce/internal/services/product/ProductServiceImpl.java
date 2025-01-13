package com.thesis.serverfurnitureecommerce.internal.services.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.serverfurnitureecommerce.domain.request.ProductRequest;
import com.thesis.serverfurnitureecommerce.domain.request.ProductSearchRequest;
import com.thesis.serverfurnitureecommerce.internal.repositories.*;
import com.thesis.serverfurnitureecommerce.internal.repositories.custom.product.IProductRepositoryCustom;
import com.thesis.serverfurnitureecommerce.model.dto.ImageDTO;
import com.thesis.serverfurnitureecommerce.model.dto.ProductDTO;
import com.thesis.serverfurnitureecommerce.model.dto.ReviewDTO;
import com.thesis.serverfurnitureecommerce.model.entity.*;
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
    CategoryRepository categoryRepository;
    SupplierRepository supplierRepository;

    @Override
    public List<ProductDTO> findAll() {
        log.info("Invoke to service find all product");
        List<ProductEntity> productEntities = productRepository.findAllByIsActive(1);
        log.info("Found {} products", productEntities.size());
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
        assert productEntity != null;
        String price = CurrencyUtils.formatCurrencyVND(productEntity.getPrice());
        productDTO.setPrice(price);
        productDTO.setImages(imageDTOS);
        productDTO.setReviewDTO(reviewDTOS);
        return productDTO;
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
    public List<ProductDTO> getWishlist(String username) {
        log.info("Invoke to service get wishlist");
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        List<WishlistEntity> wishlistEntities = wishlistRepository.findByUser(user);
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (WishlistEntity wishlist : wishlistEntities) {
            ProductEntity product = productRepository.findById(wishlist.getProduct().getId()).orElse(null);
            ProductDTO productDTO = productMapper.convertToDTO(product);
            assert product != null;
            String price = CurrencyUtils.formatCurrencyVND(product.getPrice());
            productDTO.setPrice(price);
            productDTO.setImages(getImagesByProductID(productDTO.getId()));
            productDTOS.add(productDTO);
        }
        return productDTOS;
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
    public ProductDTO updateProduct(ProductRequest product) {
        ProductEntity productEntity = productRepository.findById(product.getId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        productEntity.setDescription(product.getDescription());
        if (product.getStock() != null) {
            productEntity.setStock(product.getStock());
        }
        if (product.getCategoryID() != null) {
            CategoryEntity categoryEntity = categoryRepository.findById(product.getCategoryID())
                    .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
            productEntity.setCategory(categoryEntity);
        }
        if (product.getSupplierID() != null) {
            SupplierEntity supplierEntity = supplierRepository.findById(product.getSupplierID())
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
        CategoryEntity categoryEntity = categoryRepository.findById(productRequest.getCategoryID())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        SupplierEntity supplierEntity = supplierRepository.findById(productRequest.getSupplierID())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        productEntity.setCategory(categoryEntity);
        productEntity.setSupplier(supplierEntity);
        productEntity.setName(productRequest.getName());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setDescription(productRequest.getDescription());
        productEntity.setStock(productRequest.getStock());

        // Bước 1: Lưu ProductEntity trước để đảm bảo id được tạo
        ProductEntity savedProduct = productRepository.save(productEntity);
        log.info("Sản phẩm vừa thêm: {}", savedProduct.getId());

        // Bước 2: Thêm ImageEntity sau khi productEntity đã có id
        if (productRequest.getImage() != null) {
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setImageUrl(productRequest.getImage());
            imageEntity.setProduct(savedProduct); // Gán ProductEntity cho ImageEntity
            savedProduct.getImages().add(imageEntity); // Thêm vào danh sách ảnh
        }

        // Bước 3: Lưu lại ProductEntity (Hibernate sẽ cascade ImageEntity)
        productRepository.save(savedProduct);
    }



    private List<ImageDTO> getImagesByProductID(Integer productID) {
        ProductEntity product = productRepository.findById(productID).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        return imageRepository.getImagesByProduct(product)
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
            assert user != null;
            reviewDTO.setUsername(user.getUsername());
            reviews.add(reviewDTO);
        }
        return reviews;
    }
}
