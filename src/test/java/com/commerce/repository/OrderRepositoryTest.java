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
    void findOrderTest() {
        List<Order> orderList = orderRepository.findByRegId("sale");
        assertThat(orderList.size()).isEqualTo(1);
    }
}