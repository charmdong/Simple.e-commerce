package com.commerce.controller.form;

import com.commerce.domain.Item;
import com.commerce.dto.ItemDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemForm {

    private Long id;
    private String name;
    private Integer price;
    private Integer stockQuantity;
    private String companyName;

    public static ItemForm createItemForm(Item item) {
        ItemForm itemForm = new ItemForm();

        itemForm.setId(item.getId());
        itemForm.setName(item.getName());
        itemForm.setCompanyName(item.getCompanyName());
        itemForm.setPrice(item.getPrice());
        itemForm.setStockQuantity(item.getStockQuantity());

        return itemForm;
    }

    public static ItemForm createItemForm(ItemDto item) {
        ItemForm itemForm = new ItemForm();

        itemForm.setId(item.getId());
        itemForm.setName(item.getName());
        itemForm.setCompanyName(item.getCompanyName());
        itemForm.setPrice(item.getPrice());
        itemForm.setStockQuantity(item.getStockQuantity());

        return itemForm;
    }
}
