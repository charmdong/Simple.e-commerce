package com.commerce.controller.form;

import com.commerce.vo.CategoryVO;
import com.commerce.vo.ItemVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemForm {

    private Long id;
    private String name;
    private Integer price;
    private Integer stockQuantity;
    private String companyName;
    private String description;
    private List<CategoryVO> categories;

    public static ItemForm createItemForm(ItemVO item) {
        ItemForm itemForm = new ItemForm();

        itemForm.setId(item.getId());
        itemForm.setName(item.getName());
        itemForm.setCompanyName(item.getCompanyName());
        itemForm.setPrice(item.getPrice());
        itemForm.setStockQuantity(item.getStockQuantity());
        itemForm.setDescription(item.getDescription());
        itemForm.setCategories(item.getCategories());

        return itemForm;
    }
}
