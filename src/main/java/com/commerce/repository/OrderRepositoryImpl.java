package com.commerce.repository;

import com.commerce.domain.Order;
import com.commerce.domain.OrderStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.commerce.domain.QDelivery.delivery;
import static com.commerce.domain.QItem.item;
import static com.commerce.domain.QMember.member;
import static com.commerce.domain.QOrder.order;
import static com.commerce.domain.QOrderItem.orderItem;


@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Order> findAllWithMemberDelivery () {
        return queryFactory.selectFrom(order)
                .innerJoin(order.member, member).fetchJoin()
                .innerJoin(order.delivery, delivery).fetchJoin()
                .fetch();
    }

    @Override
    public List<Order> findAllWithItem () {
        return queryFactory.selectFrom(order)
                .innerJoin(order.member, member).fetchJoin()
                .innerJoin(order.delivery, delivery).fetchJoin()
                .innerJoin(order.orderItems, orderItem).fetchJoin()
                .innerJoin(orderItem.item, item)
                .distinct()
                .fetch();
    }

    @Override
    public List<Order> findByItemRegId (String regId, OrderStatus orderStatus) {
        return queryFactory.select(order)
                .from(order)
                .innerJoin(order.orderItems, orderItem).fetchJoin()
                .innerJoin(orderItem.item, item)
                .where(item.regId.eq(regId),
                        order.orderStatus.eq(orderStatus))
                .fetch();
    }

    @Override
    public List<Order> findByMemberId (String memberId, OrderStatus orderStatus) {
        return queryFactory.selectFrom(order)
                .innerJoin(order.member).fetchJoin()
                .where(order.member.id.eq(memberId),
                        order.orderStatus.eq(orderStatus))
                .fetch();
    }

    @Override
    public List<Order> findAllWithOrderStatus (OrderStatus orderStatus) {
        return queryFactory.selectFrom(order)
                .where(order.orderStatus.eq(orderStatus))
                .fetch();
    }

    @Override
    public List<Order> findByMemberIdWithPeriod (String memberId, OrderSearch orderSearch) {
        return queryFactory.selectFrom(order)
                .where(order.member.id.eq(memberId))
                .where(order.orderDate.between(orderSearch.getStartDate(), orderSearch.getEndDate()))
                .fetch();
    }
}
