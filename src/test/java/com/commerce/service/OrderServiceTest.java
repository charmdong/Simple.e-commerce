package com.commerce.service;

import com.commerce.domain.Cart;
import com.commerce.dto.order.CartDto;
import com.commerce.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    CartRepository cartRepository;

    @Test
    @Rollback(value = false)
    void addCartTest () {
        String userId = "sale";
        Long itemId = 2L;
        int count = 2;

        CartDto cartDto = orderService.addCart(userId, itemId, count);

        assertThat(cartDto.getItemId()).isEqualTo(2L);
    }

    @Test
    void findCartTest () {
        String userId = "sale";
        List<CartDto> cartList = orderService.findCartByUserId(userId);

        assertThat(cartList.size()).isEqualTo(1);
    }

    @Test
    @Rollback(value = false)
    void updateCartTest () {
        orderService.updateCart(14L, 4);
        Cart result = cartRepository.findById(14L).get();
        assertThat(result.getCount()).isEqualTo(4);
    }

    @Test
    @Rollback(value = false)
    void removeCartTest () {
        String userId = "sale";
        Long itemId = 2L;

        orderService.removeCart(userId, itemId);
        List<CartDto> result = orderService.findCartByUserId(userId);

        assertThat(result.size()).isEqualTo(0);
    }
}