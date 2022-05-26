package com.commerce.domain;

import lombok.Data;

@Data
public class OrderSearch {

    private String userId;
    private OrderStatus orderStatus;

}
