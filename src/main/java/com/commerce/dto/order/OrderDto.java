package com.commerce.dto.order;

import com.commerce.domain.DeliveryStatus;
import com.commerce.domain.Order;
import com.commerce.domain.OrderStatus;
import com.commerce.dto.member.MemberDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private MemberDto member;
    private List<OrderItemDto> orderItems;
    private DeliveryStatus deliveryStatus;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.member = new MemberDto(order.getMember());
        this.orderItems = order.getOrderItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.deliveryStatus = order.getDelivery().getStatus();
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getOrderStatus();
    }
}
