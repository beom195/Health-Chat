package com.beom195.health_chat.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "trainer_password")
    private String trainerPassword;

    @Column(name = "trainer_name")
    private String trainerName;

    @Column(name = "trainer_email")
    private String trainerEmail;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Trainer(Long trainerId, String trainerLoginId, String trainerPassword, String trainerName, String trainerEmail, Role role) {
        this.trainerId = trainerId;
        this.trainerLoginId = trainerLoginId;
        this.trainerPassword = trainerPassword;
        this.trainerName = trainerName;
        this.trainerEmail = trainerEmail;
        this.role = role;
    }
}
