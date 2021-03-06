package com.commerce.repository;

import com.commerce.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    void findOrderTest () {
        List<Order> orderList = orderRepository.findByItemRegId("sale", null);
        assertThat(orderList.size()).isEqualTo(1);
    }

    @Test
    void findAllWithItem () {
        List<Order> orderList = orderRepository.findAllWithItem();

        for (Order order : orderList) {
            System.out.println(order);
        }
    }

    @Test
    void findByRegId () {
        System.out.println("@@@@@");
        List<Order> orderList = orderRepository.findByItemRegId("admin", null);
        System.out.println("@@@@@");

        for (Order order : orderList) {
            System.out.println(order);
        }
    }

    @Test
    void findByMemberId () {
        List<Order> orderList = orderRepository.findByMemberId("sale", null);

        for (Order order : orderList) {
            System.out.println(order);
        }
    }
}