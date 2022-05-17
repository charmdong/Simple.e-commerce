package com.commerce.service;

import com.commerce.domain.*;
import com.commerce.dto.SessionVO;
import com.commerce.dto.order.OrderDto;
import com.commerce.repository.ItemRepository;
import com.commerce.repository.MemberRepository;
import com.commerce.repository.OrderRepository;
import com.commerce.util.ExceptionUtils;
import com.commerce.util.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    /**
     * 상품 주문
     *
     * @param userId
     * @param itemId
     * @param count
     * @return
     */
    public Long order (String userId, Long itemId, int count) {
        Member member = memberRepository.findById(userId).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.USER_NOT_FOUND));
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.ITEM_NOT_FOUND));

        // 배송정보 설정
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     *
     * @param orderId
     */
    public void cancelOrder (Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.ORDER_NOT_FOUND));

        // 주문 취소
        order.cancel();
    }

    public List<OrderDto> findOrders (HttpSession session) {
        SessionVO loginInfo = (SessionVO) session.getAttribute(SessionUtils.LOGIN_SESSION);
        return orderRepository.findByMemberId(loginInfo.getId()).stream().map(OrderDto::new).collect(Collectors.toList());
    }
}
