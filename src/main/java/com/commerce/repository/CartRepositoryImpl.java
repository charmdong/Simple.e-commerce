package com.commerce.repository;

import com.commerce.domain.Cart;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.commerce.domain.QCart.cart;
import static com.commerce.domain.QItem.item;
import static com.commerce.domain.QMember.member;

@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Cart> findAllByMemberId (String memberId) {
        return queryFactory.selectFrom(cart)
                .innerJoin(cart.item, item).fetchJoin()
                .innerJoin(cart.member, member)
                .where(member.id.eq(memberId))
                .fetch();
    }

    @Override
    public List<Cart> findByUserIdAndItemName (String userId, String itemName) {
        return queryFactory.selectFrom(cart)
                .innerJoin(cart.item, item).fetchJoin()
                .innerJoin(cart.member, member)
                .where(member.id.eq(userId))
                .where(cart.item.name.startsWith(itemName))
                .fetch();
    }
}
