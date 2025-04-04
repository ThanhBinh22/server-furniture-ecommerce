package com.thesis.serverfurnitureecommerce.application.services.category;

import com.thesis.serverfurnitureecommerce.infrastructure.persistence.CategoryRepository;
import com.thesis.serverfurnitureecommerce.domain.model.vo.CategoryVO;
import com.thesis.serverfurnitureecommerce.domain.model.entity.CategoryEntity;
import com.thesis.serverfurnitureecommerce.common.mapper.CategoryMapper;
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
    public List<CategoryVO> getAll() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<CategoryVO> categoryVOS = categoryEntities.stream().map(categoryMapper::convertToDTO).toList();
        return categoryVOS;
    }
}
