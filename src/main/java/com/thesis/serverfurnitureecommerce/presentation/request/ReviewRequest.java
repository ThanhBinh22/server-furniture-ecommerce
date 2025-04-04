package com.thesis.serverfurnitureecommerce.presentation.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewRequest {
    Long id;
    String comment;
    Integer rating;
    Integer productID;
}
