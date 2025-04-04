package com.thesis.serverfurnitureecommerce.internal.controllers.user;

import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.pkg.utils.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.services.faqs.FaqsService;
import com.thesis.serverfurnitureecommerce.internal.services.logs.UserLogService;
import com.thesis.serverfurnitureecommerce.model.dto.FaqsDTO;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.pkg.utils.annotation.ApiMessage;
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
    public ResponseEntity<APIResponse<List<FaqsDTO>>> getFaqs(HttpServletRequest httpRequest) {
        log.info("Get all faqs");
        List<FaqsDTO> faqsDTOS = faqsService.getAllFaqs();
        userLogService.log("Get all faqs", "INFO", "User require get all faqs", null, httpRequest.getRemoteAddr());
        return ResponseBuilder.buildResponse(faqsDTOS, faqsDTOS != null ? ErrorCode.FOUND : ErrorCode.NOT_FOUND);
    }
}
