package com.thesis.serverfurnitureecommerce.presentation.controllers.user;

import com.thesis.serverfurnitureecommerce.presentation.response.APIResponse;
import com.thesis.serverfurnitureecommerce.common.utils.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.application.services.faqs.FaqsService;
import com.thesis.serverfurnitureecommerce.application.services.logs.UserLogService;
import com.thesis.serverfurnitureecommerce.domain.model.vo.FaqsVO;
import com.thesis.serverfurnitureecommerce.domain.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.common.utils.annotation.ApiMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/faqs")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class FaqsController {
    FaqsService faqsService;
    UserLogService userLogService;

    @ApiMessage("Get all faqs")
    @GetMapping
    public ResponseEntity<APIResponse<List<FaqsVO>>> getFaqs(HttpServletRequest httpRequest) {
        log.info("Get all faqs");
        List<FaqsVO> faqsVOS = faqsService.getAllFaqs();
        userLogService.log("Get all faqs", "INFO", "User require get all faqs", null, httpRequest.getRemoteAddr());
        return ResponseBuilder.buildResponse(faqsVOS, faqsVOS != null ? ErrorCode.FOUND : ErrorCode.NOT_FOUND);
    }
}
