package com.thesis.serverfurnitureecommerce.internal.controllers.user;

import com.thesis.serverfurnitureecommerce.domain.request.CartRequest;
import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.domain.response.CartResponse;
import com.thesis.serverfurnitureecommerce.domain.response.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.services.cart.CartService;
import com.thesis.serverfurnitureecommerce.internal.services.logs.UserLogService;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.pkg.utils.UserUtil;
import jakarta.servlet.http.HttpServletRequest;
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
    UserLogService userLogService;

    @GetMapping("/get-quantity-in-cart")
    public ResponseEntity<APIResponse<Integer>> getQuantityInCart(HttpServletRequest httpServletRequest) {
        log.info("GET /api/user/cart/get-quantity-in-cart");
        String username = UserUtil.getUsername();
        return handleRequest(() -> {
            userLogService.log("Get quantity in cart", "INFO", "User get quantity in cart", username, httpServletRequest.getRemoteAddr());
            int quantity = cartService.getQuantityInCart(username);
            return ResponseBuilder.buildResponse(quantity, ErrorCode.FOUND);
        });
    }

    @GetMapping("/get-cart")
    public ResponseEntity<APIResponse<CartResponse>> getCart() {
        log.info("GET /api/user/cart/get-cart");
        String username = UserUtil.getUsername();
        return handleRequest(() -> {
            CartResponse cartResponse = cartService.getCart(username);
            return ResponseBuilder.buildResponse(cartResponse, ErrorCode.CREATE_SUCCESS);
        });
    }

    @PostMapping("/add-product-into-cart")
    public ResponseEntity<APIResponse<Void>> addProductIntoCart(@RequestBody CartRequest cartRequest, HttpServletRequest httpServletRequest) {
        log.info("POST /api/user/cart/add-product-into-cart with productID: {}", cartRequest.getProductID());
        return handleRequest(() -> {
            userLogService.log("Add product into cart", "INFO", "User add product into cart", UserUtil.getUsername(), httpServletRequest.getRemoteAddr());
            cartService.addCartItem(cartRequest);
            return ResponseBuilder.buildResponse(null, ErrorCode.CREATE_SUCCESS);
        });
    }

    @PutMapping("/increase-quantity")
    public ResponseEntity<APIResponse<Void>> updateProductQuantity(@RequestBody CartRequest cartRequest, HttpServletRequest httpServletRequest) {
        log.info("PATCH /api/user/cart/update-product-quantity");
        return handleRequest(() -> {
            userLogService.log("Increase product quantity", "INFO", "User increase product quantity with productID" + cartRequest.getProductID(), UserUtil.getUsername(), httpServletRequest.getRemoteAddr());
            cartService.increaseCartItemQuantity(cartRequest);
            return ResponseBuilder.buildResponse(null, ErrorCode.UPDATE_SUCCESS);
        });
    }

    @PutMapping("/decrease-quantity")
    public ResponseEntity<APIResponse<Void>> decreaseProductQuantity(@RequestBody CartRequest cartRequest, HttpServletRequest httpServletRequest) {
        log.info("PATCH /api/user/cart/decrease-product-quantity");
        return handleRequest(() -> {
            userLogService.log("Decrease product quantity", "INFO", "User decrease product quantity with productID" + cartRequest.getProductID(), UserUtil.getUsername(), httpServletRequest.getRemoteAddr());
            cartService.decreaseCartItemQuantity(cartRequest);
            return ResponseBuilder.buildResponse(null, ErrorCode.UPDATE_SUCCESS);
        });
    }

    @DeleteMapping("/delete-product-from-cart/{productID}")
    public ResponseEntity<APIResponse<Void>> deleteProductFromCart(@PathVariable Integer productID, HttpServletRequest httpServletRequest) {
        log.info("DELETE /api/user/cart/delete-product-from-cart");
        return handleRequest(() -> {
            userLogService.log("Delete product from cart", "INFO", "User delete product from cart with productID" + productID, UserUtil.getUsername(), httpServletRequest.getRemoteAddr());
            cartService.removeCartItem(productID);
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
