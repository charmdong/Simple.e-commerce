package com.commerce.service;

import com.commerce.domain.*;
import com.commerce.dto.order.CartDto;
import com.commerce.dto.order.OrderDto;
import com.commerce.repository.OrderSearch;
import com.commerce.repository.*;
import com.commerce.util.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

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

    /**
     * 주문 조회
     * @param orderSearch
     * @return
     */
    public List<OrderDto> findOrdersByCondition (OrderSearch orderSearch) {
        log.info("orderSearch={}", orderSearch);
        String userId = orderSearch.getUserId();
        OrderStatus orderStatus = orderSearch.getOrderStatus();

        if (StringUtils.hasText(userId) && orderStatus != null) {
            return orderRepository.findByMemberIdAndOrderStatus(userId, orderStatus).stream().map(OrderDto::new).collect(Collectors.toList());
        }
        else if (StringUtils.hasText(userId) && orderStatus == null) {
            return orderRepository.findByMemberId(userId).stream().map(OrderDto::new).collect(Collectors.toList());
        }
        else if (!StringUtils.hasText(userId) && orderStatus != null) {
            return orderRepository.findByOrderStatus(orderStatus).stream().map(OrderDto::new).collect(Collectors.toList());
        }
        else {
            return orderRepository.findAllWithItem().stream().map(OrderDto::new).collect(Collectors.toList());
        }
    }

    /**
     * 장바구니 추가
     * @param userId
     * @param itemId
     * @param count
     */
    public CartDto addCart (String userId, Long itemId, int count) {

        Member member = memberRepository.findById(userId).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.USER_NOT_FOUND));
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.ITEM_NOT_FOUND));

        Cart cart = Cart.createCart(member, item, count);
        cartRepository.save(cart);

        return new CartDto(cart);
    }

    /**
     * 장바구니 목록 조회
     * @param userId
     * @return
     */
    public List<CartDto> findCartByUserId (String userId) {
        return cartRepository.findAllByUserId(userId).stream().map(CartDto::new).collect(Collectors.toList());
    }

    /**
     * 장바구니 수정
     * @param cartId
     * @param count
     */
    public void updateCart (Long cartId, int count) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.CART_NOT_FOUND));
        cart.updateCart(count);
    }

    /**
     * 장바구니 삭제
     * @param userId
     * @param itemId
     */
    public void removeCart (String userId, Long itemId) {
        Member member = memberRepository.findById(userId).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.USER_NOT_FOUND));
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.ITEM_NOT_FOUND));

        cartRepository.deleteByMemberAndItem(member, item);
    }
}
