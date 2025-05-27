package com.thesis.serverfurnitureecommerce.presentation.controllers.admin;

import com.thesis.serverfurnitureecommerce.presentation.response.APIResponse;
import com.thesis.serverfurnitureecommerce.common.utils.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.presentation.controllers.BaseController;
import com.thesis.serverfurnitureecommerce.application.services.category.CategoryService;
import com.thesis.serverfurnitureecommerce.domain.model.vo.CategoryVO;
import com.thesis.serverfurnitureecommerce.domain.exception.ErrorCode;
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
    public ResponseEntity<APIResponse<List<CategoryVO>>> getCategory() {
        log.info("GET /api/admin/category");
        return handleAction(()-> {
            List<CategoryVO> categoryVOS = categoryService.getAll();
            return ResponseBuilder.buildResponse(categoryVOS, ErrorCode.SUCCESS);
        });
    }
}
