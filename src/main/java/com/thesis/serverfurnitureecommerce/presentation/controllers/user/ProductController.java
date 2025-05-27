package com.thesis.serverfurnitureecommerce.presentation.controllers.user;

import com.thesis.serverfurnitureecommerce.domain.model.vo.ProductVO;
import com.thesis.serverfurnitureecommerce.presentation.requestv2.ProductRequest;
import com.thesis.serverfurnitureecommerce.presentation.response.APIResponse;
import com.thesis.serverfurnitureecommerce.common.utils.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.presentation.controllers.BaseController;
import com.thesis.serverfurnitureecommerce.application.services.logs.UserLogService;
import com.thesis.serverfurnitureecommerce.application.services.product.ProductService;
import com.thesis.serverfurnitureecommerce.domain.exception.ErrorCode;
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
    public ResponseEntity<APIResponse<List<ProductVO>>> getAllProduct(HttpServletRequest httpRequest) {
        if (productService.findAll().isEmpty()) {
            userLogService.log("Watch product", "INFO", "User watch product in shop", null, httpRequest.getRemoteAddr());
            return ResponseBuilder.buildResponse(null, ErrorCode.NOT_FOUND);
        }
        return ResponseBuilder.buildResponse(productService.findAll(), ErrorCode.FOUND);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<APIResponse<ProductVO>> getInforProduct(@PathVariable Integer id, HttpServletRequest httpRequest) {
        userLogService.log("Watch detail product", "INFO", "User watch detail product", null, httpRequest.getRemoteAddr());
        ProductVO productVO = productService.findByProductID(id);
        if (productVO != null) {
            return ResponseBuilder.buildResponse(productVO, ErrorCode.FOUND);
        } else {
            return ResponseBuilder.buildResponse(null, ErrorCode.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<APIResponse<List<ProductVO>>> findProduct(@RequestParam Map<String, Object> search, HttpServletRequest httpServletRequest) {
        userLogService.log("Search product", "INFO", "User search product by multi field", null, httpServletRequest.getRemoteAddr());
        List<ProductVO> products = productService.findByMultiFields(search);
        return ResponseBuilder.buildResponse(products, ErrorCode.FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<APIResponse<ProductVO>> updateProduct(@RequestBody ProductRequest product){
        return handleAction(() -> {
            ProductVO productVO = productService.updateProduct(product);
            return ResponseBuilder.buildResponse(productVO, ErrorCode.SUCCESS);
        });
    }
}
