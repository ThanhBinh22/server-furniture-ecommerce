package com.thesis.serverfurnitureecommerce.application.services.cart;

import com.thesis.serverfurnitureecommerce.presentation.requestv2.CartRequest;
import com.thesis.serverfurnitureecommerce.presentation.response.CartResponse;

public interface CartService {
    void addCartItem(CartRequest cartRequest);

    void removeCartItem(Integer productID);

    void increaseCartItemQuantity(CartRequest cartRequest);

    void decreaseCartItemQuantity(CartRequest cartRequest);

    CartResponse getCart(String username);

    void clearCart(String email);

    int getQuantityInCart(String username);
}
