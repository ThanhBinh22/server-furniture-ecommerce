package com.thesis.serverfurnitureecommerce.internal.controllers.user;

import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.domain.response.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.services.logs.UserLogService;
import com.thesis.serverfurnitureecommerce.internal.services.support.SupportCustomerService;
import com.thesis.serverfurnitureecommerce.model.dto.SupportCustomerDTO;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import com.thesis.serverfurnitureecommerce.pkg.utils.annotation.ApiMessage;
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
    public ResponseEntity<APIResponse<Void>> saveContact(@RequestBody SupportCustomerDTO supportCustomerDTO, HttpServletRequest httpServletRequest) {
        try {
            userLogService.log("Save contact", "INFO", "User send contact", null, httpServletRequest.getRemoteAddr());
            supportCustomerService.saveContact(supportCustomerDTO);
            return ResponseBuilder.buildResponse(null, ErrorCode.CREATE_SUCCESS);
        } catch (AppException ex) {
            log.error("saveContact: {}", ex.getMessage());
            return ResponseBuilder.buildResponse(null, ex.getErrorCode());
        }
    }
}
