package com.thesis.serverfurnitureecommerce.internal.controllers.user;

import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.domain.response.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.services.product.IProductService;
import com.thesis.serverfurnitureecommerce.model.dto.ProductDTO;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    IProductService productService;

    @GetMapping("/{id}")
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
}
