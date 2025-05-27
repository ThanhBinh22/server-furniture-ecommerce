package com.thesis.serverfurnitureecommerce.presentation.requestv2;

public record ReviewRequest(Long id,
                            String comment,
                            Integer rating,
                            Integer productID) {
}
