package com.thesis.serverfurnitureecommerce.internal.services.review;

import com.thesis.serverfurnitureecommerce.domain.request.ReviewRequest;
import com.thesis.serverfurnitureecommerce.model.dto.ReviewDTO;

public interface IReviewService {
    void saveComment(ReviewRequest reviewRequest);

    void updateComment(ReviewRequest reviewRequest);

    void deleteComment(Long reviewID);
}
