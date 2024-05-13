package com.beom195.health_chat.dto;

import com.beom195.health_chat.domain.Role;
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
    private Role role;

    @Builder
    public TrainerDTO(Long trainerId, String trainerLoginId, String trainerName, String trainerEmail, Integer likes, Role role) {
        this.trainerId = trainerId;
        this.trainerLoginId = trainerLoginId;
        this.trainerName = trainerName;
        this.trainerEmail = trainerEmail;
        this.likes = likes;
        this.role = role;
    }
}
