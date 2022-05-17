package com.commerce.dto.order;

import com.commerce.domain.OrderItem;
import com.commerce.dto.ItemDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {

    private ItemDto item;
    private Integer orderPrice;
    private Integer count;

    public OrderItemDto(OrderItem item) {
        this.item = new ItemDto(item.getItem());
        this.orderPrice = item.getOrderPrice();
        this.count = item.getCount();
    }
}
