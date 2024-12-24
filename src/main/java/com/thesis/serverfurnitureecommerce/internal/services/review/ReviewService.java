package com.thesis.serverfurnitureecommerce.internal.services.review;

import com.thesis.serverfurnitureecommerce.domain.request.ReviewRequest;
import com.thesis.serverfurnitureecommerce.model.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getComment(Integer productID);

    void saveComment(ReviewRequest reviewRequest);

    void updateComment(ReviewRequest reviewRequest);

    void deleteComment(Long reviewID);
}
