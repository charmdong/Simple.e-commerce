package com.commerce.service;

import com.commerce.domain.Cart;
import com.commerce.vo.order.CartVO;
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
class CartServiceTest {

    @Autowired
    CartService cartService;

    @Autowired
    CartRepository cartRepository;

    @Test
    @Rollback(value = false)
    void addCartTest () {
        String userId = "sale";
        Long itemId = 2L;
        int count = 2;

        CartVO cartVO = cartService.addCart(userId, itemId, count);

        assertThat(cartVO.getItemId()).isEqualTo(2L);
    }

    @Test
    void findCartTest () {
        String userId = "sale";
        List<CartVO> cartList = cartService.findCartByUserId(userId);

        assertThat(cartList.size()).isEqualTo(1);
    }

    @Test
    @Rollback(value = false)
    void updateCartTest () {
        cartService.updateCart(14L, 4);
        Cart result = cartRepository.findById(14L).get();
        assertThat(result.getCount()).isEqualTo(4);
    }

}