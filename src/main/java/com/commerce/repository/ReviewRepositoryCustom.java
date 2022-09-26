package com.commerce.repository;

import com.commerce.domain.Review;

import java.util.List;

public interface ReviewRepositoryCustom {

    List<Review> findByUserId(String userId);
    List<Review> findByItem(Long itemId);
}
