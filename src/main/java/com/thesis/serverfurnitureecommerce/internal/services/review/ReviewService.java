package com.thesis.serverfurnitureecommerce.internal.services.review;

import com.thesis.serverfurnitureecommerce.domain.request.ReviewRequest;

public interface ReviewService {
    void saveComment(ReviewRequest reviewRequest);

    void updateComment(ReviewRequest reviewRequest);

    void deleteComment(Long reviewID);
}
