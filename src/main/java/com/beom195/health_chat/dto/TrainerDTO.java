package com.beom195.health_chat.dto;

import jakarta.persistence.Column;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class TrainerDTO {

    private Long trainerId;

    private String trainerLoginId;

    private String trainerName;

    private String trainerEmail;

    private Integer likes;

    @Builder
    public TrainerDTO(Long trainerId, String trainerLoginId, String trainerName, String trainerEmail, Integer likes) {
        this.trainerId = trainerId;
        this.trainerLoginId = trainerLoginId;
        this.trainerName = trainerName;
        this.trainerEmail = trainerEmail;
        this.likes = likes;
    }
}
