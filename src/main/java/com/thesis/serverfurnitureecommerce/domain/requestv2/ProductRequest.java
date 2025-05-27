package com.thesis.serverfurnitureecommerce.domain.requestv2;

public record ProductRequest(
        Integer id,
        String name,
        Integer categoryID,
        String description,
        Integer price,
        Integer stock,
        String image,
        Integer supplierID
) {}
