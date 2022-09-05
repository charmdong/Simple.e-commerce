package com.commerce.repository;

import com.commerce.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderSearch {

    private String userId;
    private OrderStatus orderStatus;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
