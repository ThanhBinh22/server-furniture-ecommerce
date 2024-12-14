package com.thesis.serverfurnitureecommerce.internal.controllers.user;

import com.thesis.serverfurnitureecommerce.domain.request.WishlistRequest;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WishlistController {
    ProductService productService;

    @PostMapping("/add-to-wishlist")
    public ResponseEntity<APIResponse<Void>> addToWishlist(@RequestBody WishlistRequest wishlistRequest) {
        log.info("POST /api/wishlist/add-to-wishlist");
        return handleProductAction(() -> {
            String username = getCurrentUserEmail();
            productService.saveToWishlist(wishlistRequest.getProductID(), username);
            return ResponseBuilder.buildResponse(null, ErrorCode.CREATE_SUCCESS);
        });
    }

    @GetMapping()
    public ResponseEntity<APIResponse<List<ProductDTO>>> getWishlist() {
        log.info("GET /api/wishlist");
        return handleProductAction(() -> {
            String username = getCurrentUserEmail();
            return ResponseBuilder.buildResponse(productService.getWishlist(username), ErrorCode.FOUND);
        });
    }

    @DeleteMapping("/delete-wishlist")
    public ResponseEntity<APIResponse<Void>> deleteWishlist(@RequestBody WishlistRequest wishlistRequest) {
        log.info("DELETE /api/wishlist/delete-wishlist with productID: {}", wishlistRequest.getProductID());
        return handleProductAction(() -> {
            String username = getCurrentUserEmail();
            productService.deleteWishlist(wishlistRequest.getProductID(), username);
            return ResponseBuilder.buildResponse(null, ErrorCode.DELETE_SUCCESS);
        });
    }

    private String getCurrentUserEmail() {
        log.info("username: {}", SecurityContextHolder.getContext().getAuthentication().getName());
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private <T> ResponseEntity<APIResponse<T>> handleProductAction(WishlistController.WishlistAction<T> action) {
        try {
            return action.execute();
        } catch (AppException ex) {
            log.error("Error during user action: {}", ex.getMessage());
            return ResponseBuilder.buildResponse(null, ex.getErrorCode());
        }
    }

    @FunctionalInterface
    private interface WishlistAction<T> {
        ResponseEntity<APIResponse<T>> execute();
    }
}
