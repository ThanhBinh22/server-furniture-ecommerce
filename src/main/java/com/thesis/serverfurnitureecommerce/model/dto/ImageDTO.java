package com.thesis.serverfurnitureecommerce.model.dto;

import com.thesis.serverfurnitureecommerce.model.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDTO {
    Integer id;
    Integer productID;
    String imageUrl;
}
