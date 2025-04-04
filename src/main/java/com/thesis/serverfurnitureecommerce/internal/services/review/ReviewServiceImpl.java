package com.thesis.serverfurnitureecommerce.internal.services.review;

import com.thesis.serverfurnitureecommerce.domain.requestv2.ReviewRequest;
import com.thesis.serverfurnitureecommerce.internal.repositories.ProductRepository;
import com.thesis.serverfurnitureecommerce.internal.repositories.ReviewRepository;
import com.thesis.serverfurnitureecommerce.internal.repositories.UserRepository;
import com.thesis.serverfurnitureecommerce.model.dto.ReviewDTO;
import com.thesis.serverfurnitureecommerce.model.entity.ProductEntity;
import com.thesis.serverfurnitureecommerce.model.entity.ReviewEntity;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.pkg.mapper.ReviewMapper;
import com.thesis.serverfurnitureecommerce.pkg.utils.UserUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewServiceImpl implements ReviewService {
    ReviewRepository reviewRepository;
    ProductRepository productRepository;
    UserRepository userRepository;
    ReviewMapper reviewMapper;


    @Override
    public List<ReviewDTO> getComment(Integer productID) {
        ProductEntity product = productRepository.findById(productID).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        List<ReviewEntity> reviewEntities = reviewRepository.findByProduct(product);
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        for (ReviewEntity reviewEntity : reviewEntities) {
            ReviewDTO reviewDTO = reviewMapper.convertToDTO(reviewEntity);
            reviewDTOS.add(reviewDTO);
        }
        return reviewDTOS;
    }

    @Override
    public void saveComment(ReviewRequest reviewRequest) {
        String username = UserUtil.getUsername();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        ProductEntity productEntity = productRepository.findById(reviewRequest.productID())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        ReviewEntity review = reviewMapper.convertEntityFromRequest(reviewRequest);
        review.setUser(user);
        review.setProduct(productEntity);
        reviewRepository.save(review);
    }

    @Override
    public void updateComment(ReviewRequest reviewRequest) {
        ReviewEntity review = reviewRepository.findById(reviewRequest.id())
                .orElseThrow(() -> new AppException(ErrorCode.REVIEW_NOT_FOUND));
        review.setComment(reviewRequest.comment());
        reviewRepository.save(review);
    }

    @Override
    public void deleteComment(Long reviewID) {
        reviewRepository.deleteById(reviewID);
    }
}
