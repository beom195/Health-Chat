package com.beom195.health_chat.dto;

import com.beom195.health_chat.domain.Member;
import com.beom195.health_chat.domain.Trainer;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class ReviewDTO {

    private Long reviewId;
    private Member member;
    private Trainer trainer;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public ReviewDTO(Long reviewId, Member member, Trainer trainer, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.reviewId = reviewId;
        this.member = member;
        this.trainer = trainer;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
