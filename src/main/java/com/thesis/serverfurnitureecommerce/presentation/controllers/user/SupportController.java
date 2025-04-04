package com.thesis.serverfurnitureecommerce.presentation.controllers.user;

import com.thesis.serverfurnitureecommerce.presentation.requestv2.SupportCustomerRequest;
import com.thesis.serverfurnitureecommerce.presentation.response.APIResponse;
import com.thesis.serverfurnitureecommerce.common.utils.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.application.services.logs.UserLogService;
import com.thesis.serverfurnitureecommerce.application.services.support.SupportCustomerService;
import com.thesis.serverfurnitureecommerce.domain.exception.AppException;
import com.thesis.serverfurnitureecommerce.domain.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/user/support")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SupportController {
    SupportCustomerService supportCustomerService;
    UserLogService userLogService;

    @PostMapping()
    public ResponseEntity<APIResponse<Void>> saveContact(@RequestBody SupportCustomerRequest supportCustomerRequest, HttpServletRequest httpServletRequest) {
        try {
            userLogService.log("Save contact", "INFO", "User send contact", null, httpServletRequest.getRemoteAddr());
            supportCustomerService.saveContact(supportCustomerRequest);
            return ResponseBuilder.buildResponse(null, ErrorCode.CREATE_SUCCESS);
        } catch (AppException ex) {
            log.error("saveContact: {}", ex.getMessage());
            return ResponseBuilder.buildResponse(null, ex.getErrorCode());
        }
    }
}
