package com.commerce.repository;

import com.commerce.domain.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
}
