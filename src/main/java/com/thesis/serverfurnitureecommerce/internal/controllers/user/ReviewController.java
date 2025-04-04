package com.thesis.serverfurnitureecommerce.internal.controllers.user;

import com.thesis.serverfurnitureecommerce.domain.requestv2.ReviewRequest;
import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.pkg.utils.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.controllers.BaseController;
import com.thesis.serverfurnitureecommerce.internal.services.logs.UserLogService;
import com.thesis.serverfurnitureecommerce.internal.services.review.ReviewService;
import com.thesis.serverfurnitureecommerce.model.dto.ReviewDTO;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/api/review")
public class ReviewController extends BaseController {
    ReviewService reviewService;
    UserLogService userLogService;

    @GetMapping("/{productID}")
    public ResponseEntity<APIResponse<List<ReviewDTO>>> getReviews(@PathVariable Integer productID, HttpServletRequest httpServletRequest){
        return handleAction(() -> {
            userLogService.log("Get reviews", "INFO", "User require get reviews product with productID = " + productID, null, httpServletRequest.getRemoteAddr());
            List<ReviewDTO> reviews = reviewService.getComment(productID);
            return ResponseBuilder.buildResponse(reviews, ErrorCode.SUCCESS);
        });
    }

    @PostMapping
    public ResponseEntity<APIResponse<Void>> createReview(@RequestBody ReviewRequest reviewRequest, HttpServletRequest httpServletRequest) {
        return handleAction(() -> {
            userLogService.log("Create review", "INFO", "User require create review product with productID = " + reviewRequest.productID(),null, httpServletRequest.getRemoteAddr());
            reviewService.saveComment(reviewRequest);
            return ResponseBuilder.buildResponse(null, ErrorCode.CREATE_SUCCESS);
        });
    }

    @PutMapping("/update")
    public ResponseEntity<APIResponse<Void>> updateReview(@RequestBody ReviewRequest reviewRequest, HttpServletRequest httpServletRequest) {
        return handleAction(() -> {
            userLogService.log("Update review", "INFO", "User require update review product with reviewID = " + reviewRequest.id(), null, httpServletRequest.getRemoteAddr());
            reviewService.updateComment(reviewRequest);
            return ResponseBuilder.buildResponse(null, ErrorCode.UPDATE_SUCCESS);
        });
    }

    @DeleteMapping("/delete")
    public ResponseEntity<APIResponse<Void>> deleteComment(@RequestParam Long reviewID, HttpServletRequest httpServletRequest) {
        return handleAction(() -> {
            userLogService.log("Delete review", "INFO", "User require delete review product with reviewID = " + reviewID, null, httpServletRequest.getRemoteAddr());
            reviewService.deleteComment(reviewID);
            return ResponseBuilder.buildResponse(null, ErrorCode.DELETE_SUCCESS);
        });
    }
}
