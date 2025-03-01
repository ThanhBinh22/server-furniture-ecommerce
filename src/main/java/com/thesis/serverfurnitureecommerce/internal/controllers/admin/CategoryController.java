package com.thesis.serverfurnitureecommerce.internal.controllers.admin;

import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.domain.response.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.controllers.BaseController;
import com.thesis.serverfurnitureecommerce.internal.services.category.CategoryService;
import com.thesis.serverfurnitureecommerce.model.dto.CategoryDTO;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController(value = "admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/api/admin/category")
public class CategoryController extends BaseController {
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<APIResponse<List<CategoryDTO>>> getCategory() {
        log.info("GET /api/admin/category");
        return handleAction(()-> {
            List<CategoryDTO> categoryDTOS = categoryService.getAll();
            return ResponseBuilder.buildResponse(categoryDTOS, ErrorCode.SUCCESS);
        });
    }
}
