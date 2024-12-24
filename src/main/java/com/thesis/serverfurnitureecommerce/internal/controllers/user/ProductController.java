package com.thesis.serverfurnitureecommerce.internal.controllers.user;

import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.domain.response.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.services.logs.UserLogService;
import com.thesis.serverfurnitureecommerce.internal.services.product.ProductService;
import com.thesis.serverfurnitureecommerce.model.dto.ProductDTO;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
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
    UserLogService userLogService;

    @GetMapping
    public ResponseEntity<APIResponse<List<ProductDTO>>> getAllProduct(HttpServletRequest httpRequest) {
        log.info("GET /api/product");
        if (productService.findAll().isEmpty()) {
            log.error("GET /api/product failed");
            userLogService.log("Watch product", "INFO", "User watch product in shop", null, httpRequest.getRemoteAddr());
            return ResponseBuilder.buildResponse(null, ErrorCode.NOT_FOUND);
        }
        return ResponseBuilder.buildResponse(productService.findAll(), ErrorCode.FOUND);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<APIResponse<ProductDTO>> getInforProduct(@PathVariable Integer id, HttpServletRequest httpRequest) {
        log.info("GET /api/product/{}", id);
        userLogService.log("Watch detail product", "INFO", "User watch detail product", null, httpRequest.getRemoteAddr());
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
    public ResponseEntity<APIResponse<List<ProductDTO>>> findProduct(@RequestParam Map<String, Object> search, HttpServletRequest httpServletRequest) {
        log.info("Search product by multi field with :{}", search);
        userLogService.log("Search product", "INFO", "User search product by multi field", null, httpServletRequest.getRemoteAddr());
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
