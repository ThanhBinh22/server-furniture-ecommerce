package com.thesis.serverfurnitureecommerce.presentation.controllers.user;

import com.thesis.serverfurnitureecommerce.domain.model.vo.ProductVO;
import com.thesis.serverfurnitureecommerce.presentation.request.WishlistRequest;
import com.thesis.serverfurnitureecommerce.presentation.response.APIResponse;
import com.thesis.serverfurnitureecommerce.common.utils.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.presentation.controllers.BaseController;
import com.thesis.serverfurnitureecommerce.application.services.logs.UserLogService;
import com.thesis.serverfurnitureecommerce.application.services.product.ProductService;
import com.thesis.serverfurnitureecommerce.domain.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.common.utils.UserUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WishlistController extends BaseController {
    ProductService productService;
    UserLogService userLogService;

    @PostMapping("/add-to-wishlist")
    public ResponseEntity<APIResponse<Void>> addToWishlist(@RequestBody WishlistRequest wishlistRequest, HttpServletRequest httpServletRequest) {
        return handleAction(() -> {
            String username = UserUtil.getUsername();
            userLogService.log("Add to wishlist", "INFO", "User add product to wishlist", username, httpServletRequest.getRemoteAddr());
            productService.saveToWishlist(wishlistRequest.getProductID(), username);
            return ResponseBuilder.buildResponse(null, ErrorCode.CREATE_SUCCESS);
        });
    }

    @GetMapping()
    public ResponseEntity<APIResponse<List<ProductVO>>> getWishlist(HttpServletRequest httpServletRequest) {
        return handleAction(() -> {
            String username = UserUtil.getUsername();
            userLogService.log("Get wishlist", "INFO", "User get wishlist", username, httpServletRequest.getRemoteAddr());
            return ResponseBuilder.buildResponse(productService.getWishlist(username), ErrorCode.FOUND);
        });
    }

    @DeleteMapping("/delete-wishlist")
    public ResponseEntity<APIResponse<Void>> deleteWishlist(@RequestBody WishlistRequest wishlistRequest, HttpServletRequest httpServletRequest) {
        return handleAction(() -> {
            String username = UserUtil.getUsername();
            userLogService.log("Delete wishlist", "INFO", "User delete product from wishlist", username, httpServletRequest.getRemoteAddr());
            productService.deleteWishlist(wishlistRequest.getProductID(), username);
            return ResponseBuilder.buildResponse(null, ErrorCode.DELETE_SUCCESS);
        });
    }
}
