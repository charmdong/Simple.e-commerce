package com.commerce.service;

import com.commerce.controller.form.ItemForm;
import com.commerce.controller.form.MemberForm;
import com.commerce.domain.Item;
import com.commerce.domain.Member;
import com.commerce.domain.Order;
import com.commerce.domain.OrderStatus;
import com.commerce.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    ItemService itemService;

    @Test
    void order() {
        Member member = createMember();
        Item item = createItem();

        orderService.order(member.getId(), item.getId(), 1);

        List<Order> orderList = orderRepository.findByMemberId("member1");
        assertThat(orderList.size()).isEqualTo(1);
        assertThat(orderList.get(0).getTotalPrice()).isEqualTo(2000000);
    }

    @Test
    void cancelOrder() {
        Member member = createMember();
        Item item = createItem();

        Long orderId = orderService.order(member.getId(), item.getId(), 1);
        orderService.cancelOrder(orderId);

        Order order = orderRepository.findById(orderId).get();
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.CANCEL);
    }

    private Item createItem () {
        ItemForm itemForm = new ItemForm();
        itemForm.setName("Macbook Pro");
        itemForm.setCompanyName("Apple");
        itemForm.setPrice(2000000);
        itemForm.setStockQuantity(10);
        itemForm.setDescription("M1 14inch Macbook Pro");

        Item item = Item.createItem(itemForm);
        itemService.saveItem(item);
        return item;
    }

    private Member createMember () {
        MemberForm memberForm = new MemberForm();
        memberForm.setId("member1");
        memberForm.setName("test");
        memberForm.setPassword("password1");
        memberForm.setCity("seongnam");
        memberForm.setStreet("imaero 54");
        memberForm.setZipcode("123123");

        Member member = Member.createMember(memberForm);
        memberService.joinMember(member);
        return member;
    }
}