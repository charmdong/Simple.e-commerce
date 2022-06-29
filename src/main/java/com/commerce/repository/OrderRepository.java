package com.commerce.repository;

import com.commerce.domain.Order;
import com.commerce.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

    List<Order> findByOrderStatus(OrderStatus orderStatus);
    List<Order> findByMemberIdAndOrderStatus(String userId, OrderStatus orderStatus);

}
