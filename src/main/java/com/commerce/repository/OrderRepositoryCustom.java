package com.commerce.repository;

import com.commerce.domain.Order;

import java.util.List;

public interface OrderRepositoryCustom {

    List<Order> findAllWithMemberDelivery();
    List<Order> findAllWithItem();
    List<Order> findByRegId(String regId);
    List<Order> findByMemberId(String memberId);
}
