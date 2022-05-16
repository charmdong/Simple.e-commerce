package com.commerce.dto;

import com.commerce.domain.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDto {

    private Long id;
    private String name;
    private String companyName;
    private int price;
    private int stockQuantity;

    public ItemDto (Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.name = item.getCompanyName();
        this.price = item.getPrice();
        this.stockQuantity = item.getStockQuantity();
    }
}
