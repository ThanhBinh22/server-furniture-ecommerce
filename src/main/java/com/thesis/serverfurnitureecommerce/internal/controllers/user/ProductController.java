package com.thesis.serverfurnitureecommerce.internal.controllers.user;

import com.thesis.serverfurnitureecommerce.domain.requestv2.ProductRequest;
import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.pkg.utils.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.controllers.BaseController;
import com.thesis.serverfurnitureecommerce.internal.services.logs.UserLogService;
import com.thesis.serverfurnitureecommerce.internal.services.product.ProductService;
import com.thesis.serverfurnitureecommerce.model.dto.ProductDTO;
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
public class ProductController extends BaseController {
    ProductService productService;
    UserLogService userLogService;

    @GetMapping
    public ResponseEntity<APIResponse<List<ProductDTO>>> getAllProduct(HttpServletRequest httpRequest) {
        if (productService.findAll().isEmpty()) {
            userLogService.log("Watch product", "INFO", "User watch product in shop", null, httpRequest.getRemoteAddr());
            return ResponseBuilder.buildResponse(null, ErrorCode.NOT_FOUND);
        }
        return ResponseBuilder.buildResponse(productService.findAll(), ErrorCode.FOUND);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<APIResponse<ProductDTO>> getInforProduct(@PathVariable Integer id, HttpServletRequest httpRequest) {
        userLogService.log("Watch detail product", "INFO", "User watch detail product", null, httpRequest.getRemoteAddr());
        ProductDTO productDTO = productService.findByProductID(id);
        if (productDTO != null) {
            return ResponseBuilder.buildResponse(productDTO, ErrorCode.FOUND);
        } else {
            return ResponseBuilder.buildResponse(null, ErrorCode.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<APIResponse<List<ProductDTO>>> findProduct(@RequestParam Map<String, Object> search, HttpServletRequest httpServletRequest) {
        userLogService.log("Search product", "INFO", "User search product by multi field", null, httpServletRequest.getRemoteAddr());
        List<ProductDTO> products = productService.findByMultiFields(search);
        return ResponseBuilder.buildResponse(products, ErrorCode.FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<APIResponse<ProductDTO>> updateProduct(@RequestBody ProductRequest product){
        return handleAction(() -> {
            ProductDTO productDTO = productService.updateProduct(product);
            return ResponseBuilder.buildResponse(productDTO, ErrorCode.SUCCESS);
        });
    }
}
