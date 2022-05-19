package com.commerce.dto;

import com.commerce.domain.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ItemDto {

    private Long id;
    private String name;
    private String companyName;
    private int price;
    private int stockQuantity;
    private List<CategoryDto> categories;

    public ItemDto (Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.companyName = item.getCompanyName();
        this.price = item.getPrice();
        this.stockQuantity = item.getStockQuantity();
        this.categories = item.getCategories().stream().map(CategoryDto::new).collect(Collectors.toList());
    }
}
