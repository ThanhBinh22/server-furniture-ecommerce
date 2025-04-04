package com.thesis.serverfurnitureecommerce.application.services.cart;

import com.thesis.serverfurnitureecommerce.domain.model.vo.ProductVO;
import com.thesis.serverfurnitureecommerce.presentation.requestv2.CartRequest;
import com.thesis.serverfurnitureecommerce.presentation.response.CartItemResponse;
import com.thesis.serverfurnitureecommerce.presentation.response.CartResponse;
import com.thesis.serverfurnitureecommerce.infrastructure.persistence.CartItemRepository;
import com.thesis.serverfurnitureecommerce.infrastructure.persistence.CartRepository;
import com.thesis.serverfurnitureecommerce.infrastructure.persistence.ProductRepository;
import com.thesis.serverfurnitureecommerce.infrastructure.persistence.UserRepository;
import com.thesis.serverfurnitureecommerce.domain.model.entity.CartEntity;
import com.thesis.serverfurnitureecommerce.domain.model.entity.CartItemEntity;
import com.thesis.serverfurnitureecommerce.domain.model.entity.ProductEntity;
import com.thesis.serverfurnitureecommerce.domain.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.domain.exception.AppException;
import com.thesis.serverfurnitureecommerce.domain.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.common.mapper.ProductMapper;
import com.thesis.serverfurnitureecommerce.common.mapper.UserMapper;
import com.thesis.serverfurnitureecommerce.common.utils.CurrencyUtils;
import com.thesis.serverfurnitureecommerce.common.utils.UserUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartServiceImpl implements CartService {
    CartItemRepository cartItemRepository;
    CartRepository cartRepository;
    UserRepository userRepository;
    ProductRepository productRepository;
    UserMapper userMapper;
    ProductMapper productMapper;

    @Override
    public void addCartItem(CartRequest cartRequest) {
        String username = UserUtil.getUsername();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        CartEntity cart = cartRepository.findByUser(user);
        ProductEntity product = productRepository.findById(cartRequest.productID())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        Optional<CartItemEntity> existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);

        if (existingCartItem.isPresent()) {
            CartItemEntity cartItemEntity = existingCartItem.get();
            cartItemEntity.setQuantity(cartItemEntity.getQuantity() + 1);
            cartItemRepository.save(cartItemEntity);
        } else {
            CartItemEntity cartItemEntity = CartItemEntity.create();
            cartItemEntity.setQuantity(1);
            cartItemEntity.setCart(cart);
            cartItemEntity.setProduct(product);
            cartItemEntity.setId(UUID.randomUUID().toString());
            cartItemRepository.save(cartItemEntity);
            cart.setQuantity(cart.getQuantity() + 1);
            cartRepository.save(cart);
        }
    }


    @Override   
    public void removeCartItem(Integer productID) {
        String username = UserUtil.getUsername();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        CartEntity cart = cartRepository.findByUser(user);
        ProductEntity product = productRepository.findById(productID)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        Optional<CartItemEntity> existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);

        if (existingCartItem.isPresent()) {
            CartItemEntity cartItemEntity = existingCartItem.get();
            cartItemRepository.delete(cartItemEntity);
            cart.setQuantity(cart.getQuantity() - 1);
            cartRepository.save(cart);
        }
    }

    @Override
    public void increaseCartItemQuantity(CartRequest cartRequest) {
        String username = UserUtil.getUsername();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        CartEntity cart = cartRepository.findByUser(user);
        ProductEntity product = productRepository.findById(cartRequest.productID())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        Optional<CartItemEntity> existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);

        if (existingCartItem.isPresent()) {
            CartItemEntity cartItemEntity = existingCartItem.get();
            cartItemEntity.setQuantity(cartItemEntity.getQuantity() + 1);
            cartItemRepository.save(cartItemEntity);
        } else {
            CartItemEntity newCartItem = CartItemEntity.create();
            newCartItem.setQuantity(1);
            newCartItem.setCart(cart);
            newCartItem.setProduct(product);
            newCartItem.setId(UUID.randomUUID().toString());
            cartItemRepository.save(newCartItem);
            cart.setQuantity(cart.getQuantity() + 1);
            cartRepository.save(cart);
        }
    }


    @Override
    public void decreaseCartItemQuantity(CartRequest cartRequest) {
        String username = UserUtil.getUsername();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        CartEntity cart = cartRepository.findByUser(user);
        ProductEntity product = productRepository.findById(cartRequest.productID())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        Optional<CartItemEntity> existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);

        if (existingCartItem.isPresent()) {
            CartItemEntity cartItemEntity = existingCartItem.get();
            int newQuantity = cartItemEntity.getQuantity() - 1;

            if (newQuantity > 0) {
                cartItemEntity.setQuantity(newQuantity);
                cartItemRepository.save(cartItemEntity);
            } else {
                cartItemRepository.delete(cartItemEntity);
                cart.setQuantity(cart.getQuantity() - 1);
                cartRepository.save(cart);
            }
        } else {
            throw new AppException(ErrorCode.CART_ITEM_NOT_FOUND);
        }
    }


    @Override
    public CartResponse getCart(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        CartEntity cart = cartRepository.findByUser(user);
        CartResponse cartResponse = CartResponse.create();
        cartResponse.setCartItemResponse(new ArrayList<>());
        cartResponse.setId(cart.getId());
        cartResponse.setUserVO(userMapper.toDTO(user));
        List<CartItemEntity> listCartItem = cartItemRepository.findByCart(cart);
        if (listCartItem == null) {
            return cartResponse;
        }
        double amount = 0;
        for (CartItemEntity cartItemEntity : listCartItem) {
            ProductEntity product = productRepository.findById(cartItemEntity.getProduct().getId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
            CartItemResponse cartItemResponse = CartItemResponse.create();
            cartItemResponse.setId(cartItemEntity.getId());
            cartItemResponse.setQuantity(cartItemEntity.getQuantity());
            ProductVO productVO = productMapper.convertToDTO(product);
            amount += product.getPrice() * cartItemEntity.getQuantity();
            String price = CurrencyUtils.formatCurrencyVND(product.getPrice());
            productVO.setPrice(price);
            cartItemResponse.setProductVO(productVO);
            cartResponse.getCartItemResponse().add(cartItemResponse);
        }
        cartResponse.setAmount(amount);
        cartResponse.setQuantity(listCartItem.size());
        return cartResponse;
    }

    @Override
    public void clearCart(String email) {

    }

    @Override
    public int getQuantityInCart(String username) {
        UserEntity user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return 0;
        }
        CartEntity cart = cartRepository.findByUser(user);
        return cart.getQuantity();
    }

}
