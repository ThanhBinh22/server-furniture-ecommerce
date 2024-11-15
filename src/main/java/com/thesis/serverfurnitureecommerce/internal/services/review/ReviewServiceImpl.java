package com.thesis.serverfurnitureecommerce.internal.services.review;

import com.thesis.serverfurnitureecommerce.domain.request.ReviewRequest;
import com.thesis.serverfurnitureecommerce.internal.repositories.ProductRepository;
import com.thesis.serverfurnitureecommerce.internal.repositories.ReviewRepository;
import com.thesis.serverfurnitureecommerce.internal.repositories.UserRepository;
import com.thesis.serverfurnitureecommerce.model.entity.ProductEntity;
import com.thesis.serverfurnitureecommerce.model.entity.ReviewEntity;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.pkg.mapper.ReviewMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewServiceImpl implements ReviewService {
    ReviewRepository reviewRepository;
    ProductRepository productRepository;
    UserRepository userRepository;
    ReviewMapper reviewMapper;


    @Override
    public void saveComment(ReviewRequest reviewRequest) {
        UserEntity user = userRepository.findByEmail(reviewRequest.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        ProductEntity productEntity = productRepository.findById(reviewRequest.getProductID())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        ReviewEntity review = reviewMapper.convertEntityFromRequest(reviewRequest);
        review.setUser(user);
        review.setProduct(productEntity);
        reviewRepository.save(review);
    }

    @Override
    public void updateComment(ReviewRequest reviewRequest) {
        ReviewEntity review = reviewRepository.findById(reviewRequest.getId())
                .orElseThrow(() -> new AppException(ErrorCode.REVIEW_NOT_FOUND));
        review.setComment(reviewRequest.getComment());
        reviewRepository.save(review);
    }

    @Override
    public void deleteComment(Long reviewID) {
        reviewRepository.deleteById(reviewID);
    }
}
