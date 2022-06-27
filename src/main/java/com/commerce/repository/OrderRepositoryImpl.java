package com.commerce.repository;

import com.commerce.domain.Order;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.commerce.domain.QOrder.order;
import static com.commerce.domain.QOrderItem.orderItem;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Order> findAllWithMemberDelivery () {
        return null;
    }

    @Override
    public List<Order> findAllWithItem () {
        return queryFactory.select(order)
                .from(order)
                .leftJoin(order.member).fetchJoin()
                .leftJoin(order.delivery).fetchJoin()
                .leftJoin(order.orderItems).fetchJoin()
                .leftJoin(orderItem).fetchJoin()
                .distinct().fetch();
    }

    @Override
    public List<Order> findByRegId (String regId) {
        return queryFactory.selectFrom(order)
                .innerJoin(order.orderItems)
                .innerJoin(orderItem)
                .where(order.regId.eq(regId))
                .fetch();
    }

    @Override
    public List<Order> findByMemberId (String memberId) {
        return queryFactory.selectFrom(order)
                .leftJoin(order.member).fetchJoin()
                .where(order.member.id.eq(memberId))
                .fetch();
    }
}
