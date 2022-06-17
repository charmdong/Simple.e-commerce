package com.commerce.service;

import com.commerce.vo.CategoryVO;
import com.commerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryVO> categoryList() {
        return categoryRepository.findAll().stream().map(CategoryVO::new).collect(Collectors.toList());
    }
}
