package com.beom195.health_chat.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "trainer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Trainer trainer;

    @Column(name = "review_content")
    private String reviewContent;

    @Builder
    public Review(Long reviewId, Member member, Trainer trainer, String reviewContent) {
        this.reviewId = reviewId;
        this.member = member;
        this.trainer = trainer;
        this.reviewContent = reviewContent;
    }
}
