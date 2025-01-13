package com.thesis.serverfurnitureecommerce.internal.services.category;

import com.thesis.serverfurnitureecommerce.model.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAll();
}
