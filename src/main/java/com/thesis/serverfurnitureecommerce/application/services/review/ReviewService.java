package com.thesis.serverfurnitureecommerce.application.services.review;

import com.thesis.serverfurnitureecommerce.presentation.requestv2.ReviewRequest;
import com.thesis.serverfurnitureecommerce.domain.model.vo.ReviewVO;

import java.util.List;

public interface ReviewService {
    List<ReviewVO> getComment(Integer productID);

    void saveComment(ReviewRequest reviewRequest);

    void updateComment(ReviewRequest reviewRequest);

    void deleteComment(Long reviewID);
}
