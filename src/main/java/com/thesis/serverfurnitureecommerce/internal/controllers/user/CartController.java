package com.thesis.serverfurnitureecommerce.internal.controllers.user;

import com.thesis.serverfurnitureecommerce.domain.request.CartRequest;
import com.thesis.serverfurnitureecommerce.domain.request.RemoveCartItemRequest;
import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.domain.response.CartResponse;
import com.thesis.serverfurnitureecommerce.domain.response.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.services.cart.CartService;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/api/user/cart")
public class CartController {
    CartService cartService;

    @GetMapping("/get-cart")
    public ResponseEntity<APIResponse<CartResponse>> getCart(@RequestParam String email) {
        log.info("GET /api/user/cart/get-cart");
        return handleRequest(() -> {
            CartResponse cartResponse = cartService.getCart(email);
            return ResponseBuilder.buildResponse(cartResponse, ErrorCode.CREATE_SUCCESS);
        });
    }

    @PostMapping("/add-product-into-cart")
    public ResponseEntity<APIResponse<Void>> addProductIntoCart(@RequestBody CartRequest cartRequest) {
        log.info("POST /api/user/cart/add-product-into-cart");
        return handleRequest(() -> {
            cartService.addCartItem(cartRequest);
            return ResponseBuilder.buildResponse(null, ErrorCode.CREATE_SUCCESS);
        });
    }

    @PutMapping("/increase-quantity")
    public ResponseEntity<APIResponse<Void>> updateProductQuantity(@RequestBody CartRequest cartRequest) {
        log.info("PATCH /api/user/cart/update-product-quantity");
        return handleRequest(() -> {
            cartService.increaseCartItemQuantity(cartRequest);
            return ResponseBuilder.buildResponse(null, ErrorCode.UPDATE_SUCCESS);
        });
    }

    @PutMapping("/decrease-quantity")
    public ResponseEntity<APIResponse<Void>> decreaseProductQuantity(@RequestBody CartRequest cartRequest) {
        log.info("PATCH /api/user/cart/decrease-product-quantity");
        return handleRequest(() -> {
            cartService.decreaseCartItemQuantity(cartRequest);
            return ResponseBuilder.buildResponse(null, ErrorCode.UPDATE_SUCCESS);
        });
    }

    @DeleteMapping("/delete-product-from-cart")
    public ResponseEntity<APIResponse<Void>> deleteProductFromCart(@RequestBody RemoveCartItemRequest removeCartItemRequest) {
        log.info("DELETE /api/user/cart/delete-product-from-cart");
        return handleRequest(() -> {
            cartService.removeCartItem(removeCartItemRequest);
            return ResponseBuilder.buildResponse(null, ErrorCode.DELETE_SUCCESS);
        });
    }

    private <T> ResponseEntity<APIResponse<T>> handleRequest(CartController.RequestHandler<T> handler) {
        try {
            return handler.execute();
        } catch (AppException ex) {
            log.error("Error occurred: {}", ex.getErrorCode(), ex);
            return ResponseBuilder.buildResponse(null, ex.getErrorCode());
        }
    }

    @FunctionalInterface
    private interface RequestHandler<T> {
        ResponseEntity<APIResponse<T>> execute();
    }
}
