package com.thesis.serverfurnitureecommerce.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressDTO {
    String addressLine;
    String ward;
    String district;
    String province;
    String country;
    String createdAt;
    String updatedAt;
}
