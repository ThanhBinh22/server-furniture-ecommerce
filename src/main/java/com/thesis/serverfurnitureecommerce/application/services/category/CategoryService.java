package com.thesis.serverfurnitureecommerce.application.services.category;

import com.thesis.serverfurnitureecommerce.domain.model.vo.CategoryVO;

import java.util.List;

public interface CategoryService {
    List<CategoryVO> getAll();
}
