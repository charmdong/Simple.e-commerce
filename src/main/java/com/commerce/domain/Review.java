package com.commerce.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Review {

    @Id
    @GeneratedValue
    @Column(name = "REVIEW_ID")
    private Long id;

    private String content;
    private int score; // 0 ~ 5

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    // 연관관계 메서드
    public void setItem (Item item) {
        this.item = item;
        item.getReviews().add(this);
    }

    // 생성 메서드
    public static Review createReview() {

    }
}
