package com.thesis.serverfurnitureecommerce.domain.responsev2;

import com.thesis.serverfurnitureecommerce.model.dto.UserDTO;
import lombok.Builder;
import java.util.List;

@Builder
public record CartResponse(
        String id,
        UserDTO userDTO,
        List<CartItemResponse> cartItemResponse,
        Integer quantity,
        Double amount
) {
    public static CartResponse create() {
        return new CartResponse(null, null, null, 0, 0.0);
    }
}
