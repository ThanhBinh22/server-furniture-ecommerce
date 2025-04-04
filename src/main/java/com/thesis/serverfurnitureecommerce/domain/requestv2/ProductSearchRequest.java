package com.thesis.serverfurnitureecommerce.domain.requestv2;

public record ProductSearchRequest(
        String name,
        String category,
        String supplier,
        String room,
        Long minPrice,
        Long maxPrice
) {}
