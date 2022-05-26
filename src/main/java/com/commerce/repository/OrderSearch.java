package com.commerce.repository;

import com.commerce.domain.OrderStatus;
import lombok.Data;

@Data
public class OrderSearch {

    private String userId;
    private OrderStatus orderStatus;
}
