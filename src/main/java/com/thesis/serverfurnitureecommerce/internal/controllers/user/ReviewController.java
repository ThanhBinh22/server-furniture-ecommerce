package com.thesis.serverfurnitureecommerce.internal.controllers.user;

import com.thesis.serverfurnitureecommerce.domain.request.ReviewRequest;
import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.domain.response.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.services.review.IReviewService;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/api/review")
public class ReviewController {
    IReviewService reviewService;

    @PostMapping
    public ResponseEntity<APIResponse<Void>> createReview(@RequestBody ReviewRequest reviewRequest) {
        log.info("POST /api/review");
        return handleReviewAction(() -> {
            reviewService.saveComment(reviewRequest);
            return ResponseBuilder.buildResponse(null, ErrorCode.CREATE_SUCCESS);
        });
    }

    @PutMapping("/update")
    public ResponseEntity<APIResponse<Void>> updateReview(@RequestBody ReviewRequest reviewRequest) {
        log.info("Request to update review");
        return handleReviewAction(() -> {
            reviewService.updateComment(reviewRequest);
            return ResponseBuilder.buildResponse(null, ErrorCode.UPDATE_SUCCESS);
        });
    }

    @DeleteMapping("/delete")
    public ResponseEntity<APIResponse<Void>> deleteComment(@RequestParam Long reviewID) {
        log.info("Request to delete review");
        return handleReviewAction(() -> {
            reviewService.deleteComment(reviewID);
            return ResponseBuilder.buildResponse(null, ErrorCode.DELETE_SUCCESS);
        });
    }

    private <T> ResponseEntity<APIResponse<T>> handleReviewAction(ReviewAction<T> action) {
        try {
            return action.execute();
        } catch (AppException ex) {
            log.error("Error during user action: {}", ex.getMessage());
            return ResponseBuilder.buildResponse(null, ex.getErrorCode());
        }
    }

    @FunctionalInterface
    private interface ReviewAction<T> {
        ResponseEntity<APIResponse<T>> execute();
    }
}
