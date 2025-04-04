package com.thesis.serverfurnitureecommerce.internal.services.cart;

import com.thesis.serverfurnitureecommerce.domain.requestv2.CartRequest;
import com.thesis.serverfurnitureecommerce.domain.response.CartItemResponse;
import com.thesis.serverfurnitureecommerce.domain.response.CartResponse;
import com.thesis.serverfurnitureecommerce.internal.repositories.CartItemRepository;
import com.thesis.serverfurnitureecommerce.internal.repositories.CartRepository;
import com.thesis.serverfurnitureecommerce.internal.repositories.ProductRepository;
import com.thesis.serverfurnitureecommerce.internal.repositories.UserRepository;
import com.thesis.serverfurnitureecommerce.model.dto.ProductDTO;
import com.thesis.serverfurnitureecommerce.model.entity.CartEntity;
import com.thesis.serverfurnitureecommerce.model.entity.CartItemEntity;
import com.thesis.serverfurnitureecommerce.model.entity.ProductEntity;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.pkg.mapper.ProductMapper;
import com.thesis.serverfurnitureecommerce.pkg.mapper.UserMapper;
import com.thesis.serverfurnitureecommerce.pkg.utils.CurrencyUtils;
import com.thesis.serverfurnitureecommerce.pkg.utils.UserUtil;
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
        cartResponse.setUserDTO(userMapper.toDTO(user));
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
            ProductDTO productDTO = productMapper.convertToDTO(product);
            amount += product.getPrice() * cartItemEntity.getQuantity();
            String price = CurrencyUtils.formatCurrencyVND(product.getPrice());
            productDTO.setPrice(price);
            cartItemResponse.setProductDTO(productDTO);
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
