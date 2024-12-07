package com.thesis.serverfurnitureecommerce.domain.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WishlistRequest {
    Integer productID;
    String email;
}
