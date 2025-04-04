package com.thesis.serverfurnitureecommerce.application.services.review;

import com.thesis.serverfurnitureecommerce.domain.model.vo.ReviewVO;
import com.thesis.serverfurnitureecommerce.presentation.requestv2.ReviewRequest;
import com.thesis.serverfurnitureecommerce.infrastructure.persistence.ProductRepository;
import com.thesis.serverfurnitureecommerce.infrastructure.persistence.ReviewRepository;
import com.thesis.serverfurnitureecommerce.infrastructure.persistence.UserRepository;
import com.thesis.serverfurnitureecommerce.domain.model.entity.ProductEntity;
import com.thesis.serverfurnitureecommerce.domain.model.entity.ReviewEntity;
import com.thesis.serverfurnitureecommerce.domain.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.domain.exception.AppException;
import com.thesis.serverfurnitureecommerce.domain.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.common.mapper.ReviewMapper;
import com.thesis.serverfurnitureecommerce.common.utils.UserUtil;
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
    public List<ReviewVO> getComment(Integer productID) {
        ProductEntity product = productRepository.findById(productID).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        List<ReviewEntity> reviewEntities = reviewRepository.findByProduct(product);
        List<ReviewVO> reviewVOS = new ArrayList<>();
        for (ReviewEntity reviewEntity : reviewEntities) {
            ReviewVO reviewVO = reviewMapper.convertToDTO(reviewEntity);
            reviewVOS.add(reviewVO);
        }
        return reviewVOS;
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
