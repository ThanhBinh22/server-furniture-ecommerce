package com.thesis.serverfurnitureecommerce.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageVO {
    Integer id;
    Integer productID;
    String imageUrl;
}
