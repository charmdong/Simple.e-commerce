package com.commerce.repository;

import com.commerce.domain.Cart;

import java.util.List;

public interface CartRepositoryCustom {

    List<Cart> findAllByMemberId(String memberId);

    List<Cart> findByUserIdAndItemName(String userId, String itemName);
}
