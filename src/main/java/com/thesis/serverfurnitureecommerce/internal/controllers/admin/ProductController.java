package com.thesis.serverfurnitureecommerce.internal.controllers.admin;

import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.domain.response.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.services.product.ProductService;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "adminProductController")
@RequiredArgsConstructor
@RequestMapping("/api/admin/product")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseBuilder.buildResponse(null, ErrorCode.SUCCESS);
    }
}
