package com.commerce.vo;

import com.commerce.domain.Category;
import lombok.Data;

@Data
public class CategoryVO {

    private Long id;
    private String name;

    public CategoryVO (Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
