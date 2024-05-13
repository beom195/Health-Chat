package com.beom195.health_chat.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    private Long trainerId;

    @Column(name = "trainer_login_id")
    private String trainerLoginId;

    @Column(name = "trainer_name")
    private String trainerName;

    @Column(name = "trainer_email")
    private String trainerEmail;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "likes")
    private Integer likes;

    @Builder
    public Trainer(Long trainerId, String trainerLoginId, String trainerName, String trainerEmail, Role role, Integer likes) {
        this.trainerId = trainerId;
        this.trainerLoginId = trainerLoginId;
        this.trainerName = trainerName;
        this.trainerEmail = trainerEmail;
        this.role = role;
        this.likes = likes;
    }
}
