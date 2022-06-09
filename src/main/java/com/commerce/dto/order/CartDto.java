package com.commerce.dto.order;

import com.commerce.domain.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class CartDto {

    private Long cartId;
    private Long itemId;
    private String itemName;
    private int price;
    private int count;

    public CartDto(Cart cart) {
        this.cartId = cart.getId();
        this.itemId = cart.getItem().getId();
        this.itemName = cart.getItem().getName();
        this.price = cart.getItem().getPrice();
        this.count = cart.getCount();
    }
}
