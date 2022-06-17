package com.commerce.vo.order;

import com.commerce.domain.DeliveryStatus;
import com.commerce.domain.Order;
import com.commerce.domain.OrderStatus;
import com.commerce.vo.member.MemberVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderVO {

    private Long id;
    private MemberVO member;
    private List<OrderItemVO> orderItems;
    private DeliveryStatus deliveryStatus;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;

    public OrderVO (Order order) {
        this.id = order.getId();
        this.member = new MemberVO(order.getMember());
        this.orderItems = order.getOrderItems().stream().map(OrderItemVO::new).collect(Collectors.toList());
        this.deliveryStatus = order.getDelivery().getStatus();
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getOrderStatus();
    }
}
