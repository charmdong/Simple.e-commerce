package com.commerce.vo.order;

import com.commerce.domain.OrderItem;
import com.commerce.vo.ItemVO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemVO {

    private ItemVO item;
    private Integer orderPrice;
    private Integer count;

    public OrderItemVO (OrderItem item) {
        this.item = new ItemVO(item.getItem());
        this.orderPrice = item.getOrderPrice();
        this.count = item.getCount();
    }
}
