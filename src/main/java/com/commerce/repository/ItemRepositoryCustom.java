package com.commerce.repository;

import com.commerce.domain.Item;

import java.util.List;

/**
 * ItemRepositoryCustom
 *
 * @author donggun
 * @since 2022/09/28
 */
public interface ItemRepositoryCustom {

    List<Item> findAllItemList();
    List<Item> findByRegId(String userId);
}
