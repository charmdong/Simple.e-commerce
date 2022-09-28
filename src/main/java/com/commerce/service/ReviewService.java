package com.commerce.service;

import com.commerce.domain.Item;
import com.commerce.domain.Review;
import com.commerce.repository.ItemRepository;
import com.commerce.repository.ReviewRepository;
import com.commerce.util.ExceptionUtils;
import com.commerce.vo.review.CreateReviewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * ReviewService
 *
 * @author donggun
 * @since 2022/09/28
 */
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ItemRepository itemRepository;

    /**
     * 리뷰 등록
     * @param reviewVO
     * @param itemId
     * @return
     */
    public Long insertReview(CreateReviewVO reviewVO, Long itemId) {

        Item item = itemRepository.findById(itemId).orElseThrow(() -> new NoSuchElementException(ExceptionUtils.ITEM_NOT_FOUND));

        Review review = Review.createReview(reviewVO, item);
        Review newReview = reviewRepository.save(review);

        return newReview.getId();
    }

    public void updateReview() {

    }

    public void deleteReview() {

    }
}
