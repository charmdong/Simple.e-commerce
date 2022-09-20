package com.commerce.vo.review;

import com.commerce.domain.Review;
import lombok.Data;

@Data
public class CreateReviewVO {

    private String content;
    private int score;

    public CreateReviewVO (Review review) {
        this.content = review.getContent();
        this.score = review.getScore();
    }
}
