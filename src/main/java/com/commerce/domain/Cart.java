package com.commerce.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "CART_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;
    private int count;

    public static Cart createCart(Member member, Item item, int count) {

        Cart cart = new Cart();

        cart.member = member;
        cart.item = item;
        cart.count = count;

        return cart;
    }

}
