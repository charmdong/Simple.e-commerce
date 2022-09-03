package com.commerce.repository;

import com.commerce.domain.Order;
import com.commerce.domain.OrderStatus;

import java.util.List;

public interface OrderRepositoryCustom {

    List<Order> findAllWithMemberDelivery();
    List<Order> findAllWithItem();
    List<Order> findByItemRegId(String regId, OrderStatus orderStatus);
    List<Order> findByMemberId(String memberId, OrderStatus orderStatus);
    List<Order> findAllWithOrderStatus(OrderStatus orderStatus);
    List<Order> findByMemberIdWithPeriod(String memberId, OrderSearch orderSearch);
}
