package com.commerce.service;

import com.commerce.domain.*;
import com.commerce.repository.ItemRepository;
import com.commerce.repository.MemberRepository;
import com.commerce.repository.OrderRepository;
import com.commerce.repository.OrderSearch;
import com.commerce.util.ExceptionUtils;
import com.commerce.vo.order.OrderVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public List<OrderVO> findOrdersByCondition (Role role, OrderSearch orderSearch) {

        String userId = orderSearch.getUserId();
        OrderStatus orderStatus = orderSearch.getOrderStatus();

        // TODO 페이징 적용
        // 판매자
        if (role.equals(Role.SALE)) {
            if (orderSearch.getStartDate() != null) {
                return orderRepository.findByMemberIdWithPeriod(userId, orderSearch).stream().map(OrderVO::new).collect(Collectors.toList());
            } else {
                return orderRepository.findByItemRegId(userId, orderStatus).stream().map(OrderVO::new).collect(Collectors.toList());
            }
        }
        // 일반 사용자
        else if(role.equals(Role.USER)) {
            return orderRepository.findByMemberId(userId, orderStatus).stream().map(OrderVO::new).collect(Collectors.toList());
        }
        // 관리자
        else {
            return orderRepository.findAllWithOrderStatus(orderStatus).stream().map(OrderVO::new).collect(Collectors.toList());
        }
    }


}
