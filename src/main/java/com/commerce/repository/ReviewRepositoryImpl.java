package com.commerce.repository;

import com.commerce.domain.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.commerce.domain.QItem.item;
import static com.commerce.domain.QReview.review;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> findByUserId (String userId) {
        return queryFactory.selectFrom(review)
                .where(review.regId.eq(userId))
                .fetch();
    }

    @Override
    public List<Review> findByItem (Long itemId) {
        return queryFactory.selectFrom(review)
                .innerJoin(review.item, item).fetchJoin()
                .where(item.id.eq(itemId))
                .fetch();
    }
}
