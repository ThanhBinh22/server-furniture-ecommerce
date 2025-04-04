package com.thesis.serverfurnitureecommerce.presentation.controllers.admin;

import com.thesis.serverfurnitureecommerce.domain.model.vo.SupplierVO;
import com.thesis.serverfurnitureecommerce.presentation.response.APIResponse;
import com.thesis.serverfurnitureecommerce.common.utils.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.presentation.controllers.BaseController;
import com.thesis.serverfurnitureecommerce.application.services.supplier.SupplierService;
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

@RestController(value = "adminSupplierController")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/api/admin/supplier")
public class SupplierController extends BaseController {
    SupplierService supplierService;

    @GetMapping
    public ResponseEntity<APIResponse<List<SupplierVO>>> getSupplier() {
        return handleAction(() -> {
            List<SupplierVO> supplierVOS = supplierService.getAll();
            return ResponseBuilder.buildResponse(supplierVOS, ErrorCode.SUCCESS);
        });
    }

}
