package com.thesis.serverfurnitureecommerce.domain.model.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierVO {
    Integer id;
    String name;
    String contactEmail;
    String contactPhone;
    String address;
    String country;
    String website;
    Short isActive;
}
