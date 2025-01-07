package com.thesis.serverfurnitureecommerce.internal.services.category;

import com.thesis.serverfurnitureecommerce.internal.repositories.CategoryRepository;
import com.thesis.serverfurnitureecommerce.model.dto.CategoryDTO;
import com.thesis.serverfurnitureecommerce.model.entity.CategoryEntity;
import com.thesis.serverfurnitureecommerce.pkg.mapper.CategoryMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CategoryServiceImpl implements CategoryService{
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> getAll() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = categoryEntities.stream().map(categoryMapper::convertToDTO).toList();
        return categoryDTOS;
    }
}
