package com.thesis.serverfurnitureecommerce.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryDTO {
    Integer id;
    String name;
    Boolean isActive = true;
}
