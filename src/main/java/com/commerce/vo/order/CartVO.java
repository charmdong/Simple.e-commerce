package com.commerce.vo.order;

import com.commerce.domain.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class CartVO {

    private Long id;
    private Long itemId;
    private String itemName;
    private int price;
    private int count;

    public CartVO (Cart cart) {
        this.id = cart.getId();
        this.itemId = cart.getItem().getId();
        this.itemName = cart.getItem().getName();
        this.price = cart.getItem().getPrice();
        this.count = cart.getCount();
    }
}
