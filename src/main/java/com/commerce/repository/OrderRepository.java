package com.commerce.repository;

import com.commerce.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o" +
            " join fetch o.member m" +
            " join fetch o.delivery d")
    List<Order> findAllWithMemberDelivery();

    @Query("select distinct o from Order o" +
            " join fetch o.member m" +
            " join fetch o.delivery d" +
            " join fetch o.orderItems oi" +
            " join fetch oi.item i")
    List<Order> findAllWithItem();

    List<Order> findByMemberId(String userId);
}