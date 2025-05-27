package com.thesis.serverfurnitureecommerce.domain.model.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryVO {
    Integer id;
    String name;
    Boolean isActive = true;
}
