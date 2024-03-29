package com.commerce.domain;

import com.commerce.controller.form.ItemForm;
import com.commerce.exception.NotEnoughStockException;
import com.commerce.util.ExceptionUtils;
import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Item extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
    private String companyName;
    private String description;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    // 생성자 메서드
    public static Item createItem(ItemForm form) {
        Item item = new Item();

        item.name = form.getName();
        item.companyName = form.getCompanyName();
        item.price = form.getPrice();
        item.stockQuantity = form.getStockQuantity();
        item.description = form.getDescription();

        return item;
    }

    // 비즈니스 로직
    /**
     * update
     */
    public void changeInfo(ItemForm request) {
        if (StringUtils.hasText(request.getName())) {
            this.name = request.getName();
        }

        if (StringUtils.hasText(request.getCompanyName())) {
            this.companyName = request.getCompanyName();
        }

        if (request.getPrice() != null) {
            this.price = request.getPrice();
        }

        if (request.getStockQuantity() != null) {
            this.stockQuantity = request.getStockQuantity();
        }

        if (StringUtils.hasText(request.getDescription())) {
            this.description = request.getDescription();
        }
    }

    /**
     * stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;

        if(restStock < 0) {
            throw new NotEnoughStockException(ExceptionUtils.NO_MORE_STOCK);
        }

        this.stockQuantity = restStock;
    }
}
