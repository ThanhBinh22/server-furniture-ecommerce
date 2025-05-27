package com.thesis.serverfurnitureecommerce.domain.requestv2;

public record ReviewRequest(Long id,
                            String comment,
                            Integer rating,
                            Integer productID) {
}
