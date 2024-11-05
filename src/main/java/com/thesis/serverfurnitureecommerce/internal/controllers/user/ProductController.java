package com.thesis.serverfurnitureecommerce.internal.controllers.user;

import com.thesis.serverfurnitureecommerce.domain.request.ProductSearchRequest;
import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.domain.response.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.services.product.IProductService;
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
    IProductService productService;

    @GetMapping
    public ResponseEntity<APIResponse<List<ProductDTO>>> getAllProduct() {
        log.info("GET /api/product");
        if (productService.findAll().isEmpty()) {
            log.error("GET /api/product failed");
            return ResponseBuilder.buildResponse(null, ErrorCode.NOT_FOUND);
        }
        return ResponseBuilder.buildResponse(productService.findAll(), ErrorCode.FOUND);
    }

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


//    @GetMapping("/search")
//    public ResponseEntity<APIResponse<List<ProductDTO>>> findProduct(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String category,
//            @RequestParam(required = false) String supplier,
//            @RequestParam(required = false) Double minPrice,
//            @RequestParam(required = false) String room,
//            @RequestParam(required = false) Double maxPrice) {
//
//        log.info("Request to search product: name={}, category={}, supplier={}, minPrice={}, maxPrice={}, room={}",
//                name, category, supplier, minPrice, maxPrice, room);
//        try {
//            ProductSearchRequest productSearchRequest = new ProductSearchRequest();
//            productSearchRequest.setName(name);
//            productSearchRequest.setCategory(category);
//            productSearchRequest.setSupplier(supplier);
//            productSearchRequest.setRoom(room);
//            productSearchRequest.setMinPrice(minPrice);
//            productSearchRequest.setMaxPrice(maxPrice);
//
//            List<ProductDTO> products = productService.findByMultiFields(productSearchRequest);
//            return ResponseBuilder.buildResponse(products, ErrorCode.FOUND);
//        } catch (Exception ex) {
//            throw new AppException(ErrorCode.INVALID_REQUEST);
//        }
//    }

    @GetMapping("/search")
    public ResponseEntity<APIResponse<List<ProductDTO>>> findProduct(@RequestParam Map<String, Object> search) {
        log.info("Search product by multi field");
//        try {
            List<ProductDTO> products = productService.findByMultiFields(search);
            return ResponseBuilder.buildResponse(products, ErrorCode.FOUND);
//        } catch (Exception ex) {
//            throw new AppException(ErrorCode.INVALID_REQUEST);
//        }
    }
}
