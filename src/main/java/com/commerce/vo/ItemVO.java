package com.commerce.vo;

import com.commerce.domain.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ItemVO {

    private Long id;
    private String name;
    private String companyName;
    private int price;
    private int stockQuantity;
    private String description;
    private List<CategoryVO> categories;

    public ItemVO (Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.companyName = item.getCompanyName();
        this.price = item.getPrice();
        this.stockQuantity = item.getStockQuantity();
        this.description = item.getDescription();
        this.categories = item.getCategories().stream().map(CategoryVO::new).collect(Collectors.toList());
    }
}
