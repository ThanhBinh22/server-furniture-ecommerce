package com.thesis.serverfurnitureecommerce.presentation.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RemoveCartItemRequest {
    Integer productID;
}
