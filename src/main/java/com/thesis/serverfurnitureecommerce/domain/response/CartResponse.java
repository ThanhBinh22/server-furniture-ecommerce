package com.thesis.serverfurnitureecommerce.domain.response;

import com.thesis.serverfurnitureecommerce.model.dto.UserDTO;
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
    UserDTO userDTO;
    List<CartItemResponse> cartItemResponse;
    String amount;

    public static CartResponse create(){
        return new CartResponse();
    }
}
