package com.thesis.serverfurnitureecommerce.internal.controllers.user;

import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.domain.response.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.services.product.ProductService;
import com.thesis.serverfurnitureecommerce.model.dto.ProductDTO;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;

    @GetMapping
    public ResponseEntity<APIResponse<List<ProductDTO>>> getAllProduct() {
        log.info("GET /api/product");
        if (productService.findAll().isEmpty()) {
            log.error("GET /api/product failed");
            return ResponseBuilder.buildResponse(null, ErrorCode.NOT_FOUND);
        }
        return ResponseBuilder.buildResponse(productService.findAll(), ErrorCode.FOUND);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<APIResponse<ProductDTO>> getInforProduct(@PathVariable Integer id) {
        log.info("GET /api/product/{}", id);
        ProductDTO productDTO = productService.findByProductID(id);
        if (productDTO != null) {
            log.info("GET /api/product/{} success", id);
            return ResponseBuilder.buildResponse(productDTO, ErrorCode.FOUND);
        } else {
            log.error("GET /api/product/{} failed", id);
            return ResponseBuilder.buildResponse(null, ErrorCode.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<APIResponse<List<ProductDTO>>> findProduct(@RequestParam Map<String, Object> search) {
        log.info("Search product by multi field");
            List<ProductDTO> products = productService.findByMultiFields(search);
            return ResponseBuilder.buildResponse(products, ErrorCode.FOUND);
    }

    private <T> ResponseEntity<APIResponse<T>> handleProductAction(ProductController.ProductAction<T> action) {
        try {
            return action.execute();
        } catch (AppException ex) {
            log.error("Error during user action: {}", ex.getMessage());
            return ResponseBuilder.buildResponse(null, ex.getErrorCode());
        }
    }

    @FunctionalInterface
    private interface ProductAction<T> {
        ResponseEntity<APIResponse<T>> execute();
    }
}
