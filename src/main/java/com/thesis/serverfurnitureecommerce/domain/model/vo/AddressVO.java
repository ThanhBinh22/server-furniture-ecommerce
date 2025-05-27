package com.thesis.serverfurnitureecommerce.domain.model.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressVO {
    String addressLine;
    String ward;
    String district;
    String province;
    String country;
    String createdAt;
    String updatedAt;
}
