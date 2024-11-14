package com.thesis.serverfurnitureecommerce.internal.services.cart;

import com.thesis.serverfurnitureecommerce.domain.request.CartRequest;
import com.thesis.serverfurnitureecommerce.domain.request.RemoveCartItemRequest;
import com.thesis.serverfurnitureecommerce.domain.response.CartResponse;

public interface CartService {
    void addCartItem(CartRequest cartRequest);

    void removeCartItem(RemoveCartItemRequest removeCartItemRequest);

    void increaseCartItemQuantity(CartRequest cartRequest);

    void decreaseCartItemQuantity(CartRequest cartRequest);

    CartResponse getCart(String email);

    void clearCart(String email);
}
