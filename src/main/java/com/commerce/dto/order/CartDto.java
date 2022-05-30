package com.commerce.dto.order;

import com.commerce.domain.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDto {

    private Long cartId;
    private Long itemId;
    private int price;
    private int count;

    public CartDto(Cart cart) {
        this.cartId = cart.getId();
        this.itemId = cart.getItem().getId();
        this.price = cart.getItem().getPrice();
        this.count = cart.getCount();
    }
}
