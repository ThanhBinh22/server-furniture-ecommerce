package com.thesis.serverfurnitureecommerce.presentation.response;

import com.thesis.serverfurnitureecommerce.domain.model.vo.UserVO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponse {
    String id;
    UserVO userVO;
    List<CartItemResponse> cartItemResponse;
    Integer quantity;
    Double amount;

    public static CartResponse create(){
        return new CartResponse();
    }
}
