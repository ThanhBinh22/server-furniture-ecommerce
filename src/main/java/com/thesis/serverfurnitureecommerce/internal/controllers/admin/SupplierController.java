package com.thesis.serverfurnitureecommerce.internal.controllers.admin;

import com.thesis.serverfurnitureecommerce.domain.response.APIResponse;
import com.thesis.serverfurnitureecommerce.domain.response.ResponseBuilder;
import com.thesis.serverfurnitureecommerce.internal.services.supplier.SupplierService;
import com.thesis.serverfurnitureecommerce.model.dto.SupplierDTO;
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

@RestController(value = "adminSupplierController")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/api/admin/supplier")
public class SupplierController {
    SupplierService supplierService;

    @GetMapping
    public ResponseEntity<APIResponse<List<SupplierDTO>>> getSupplier() {
        log.info("GET /api/admin/supplier");
        return handleSupplierAction(() -> {
            List<SupplierDTO> supplierDTOS = supplierService.getAll();
            return ResponseBuilder.buildResponse(supplierDTOS, ErrorCode.SUCCESS);
        });
    }

    private <T> org.springframework.http.ResponseEntity<APIResponse<T>> handleSupplierAction(SupplierController.SupplierAction<T> action) {
        try {
            return action.execute();
        } catch (AppException ex) {
            log.error("Error during user action: {}", ex.getMessage());
            return ResponseBuilder.buildResponse(null, ex.getErrorCode());
        }
    }

    @FunctionalInterface
    private interface SupplierAction<T> {
        ResponseEntity<APIResponse<T>> execute();
    }
}
