package com.thesis.serverfurnitureecommerce.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierDTO {
    Integer id;
    String name;
    String contactEmail;
    String contactPhone;
    String address;
    String country;
    String website;
    Short isActive;
}
