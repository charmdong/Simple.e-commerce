package com.commerce.repository;

import com.commerce.domain.Item;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.commerce.domain.QItem.item;
import static com.commerce.domain.QReview.review;

/**
 * ItemRepositoryCustomImpl
 *
 * @author donggun
 * @since 2022/09/28
 */
@RequiredArgsConstructor
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Item> findAllItemList () {
        return queryFactory.selectFrom(item)
                .innerJoin(review.item, item).fetchJoin()
                .fetch();
    }

    @Override
    public List<Item> findByRegId (String userId) {
        return queryFactory.selectFrom(item)
                .innerJoin(review.item, item).fetchJoin()
                .where(item.regId.eq(userId))
                .fetch();
    }
}
