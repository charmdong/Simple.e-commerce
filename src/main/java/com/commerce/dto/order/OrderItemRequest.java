package com.commerce.dto.order;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemRequest {

    private Long itemId;
    private Integer count;

    public OrderItemRequest(Long itemId, Integer count) {
        this.itemId = itemId;
        this.count = count;
    }
}
