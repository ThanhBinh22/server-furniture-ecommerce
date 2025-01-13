package com.thesis.serverfurnitureecommerce.internal.controllers.admin;

import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.domain.response.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.services.logs.UserLogService;
import com.thesis.serverfurnitureecommerce.internal.services.user.UserService;
import com.thesis.serverfurnitureecommerce.model.dto.UserDTO;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "adminUserController")
@RequestMapping("/api/admin/user")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;
    UserLogService userLogService;

    @GetMapping("/get-user")
    public ResponseEntity<APIResponse<List<UserDTO>>> getUser(HttpServletRequest httpServletRequest) {
        log.info("Get user");
        userLogService.log("Get user", "INFO", "Admin get user", null, httpServletRequest.getRemoteAddr());
        return handleUserAction(() -> {
            List<UserDTO> users = userService.getAllUser();
            return ResponseBuilder.buildResponse(users, ErrorCode.SUCCESS);
        });
    }

    @GetMapping("/block-unblock/{id}")
    public ResponseEntity<APIResponse<Void>> blockUser(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        log.info("Block user {}", id);
        userLogService.log("Block user", "INFO", "Admin block user", null, httpServletRequest.getRemoteAddr());
        return handleUserAction(() -> {
            userService.blockUser(id);
            return ResponseBuilder.buildResponse(null, ErrorCode.SUCCESS);
        });
    }

    @GetMapping("/get-all-user")
    public ResponseEntity<APIResponse<List<UserDTO>>> getAllUser(HttpServletRequest httpServletRequest) {
        log.info("Get all user");
        userLogService.log("Get all user", "INFO", "Admin get all user", null, httpServletRequest.getRemoteAddr());
        return handleUserAction(() -> {
            List<UserDTO> users = userService.getAllUser();
            return ResponseBuilder.buildResponse(users, ErrorCode.SUCCESS);
        });
    }

    private <T> ResponseEntity<APIResponse<T>> handleUserAction(UserController.UserAction<T> action) {
        try {
            return action.execute();
        } catch (AppException ex) {
            log.error("Error during user action: {}", ex.getMessage());
            return ResponseBuilder.buildResponse(null, ex.getErrorCode());
        }
    }

    @FunctionalInterface
    private interface UserAction<T> {
        ResponseEntity<APIResponse<T>> execute();
    }
}
