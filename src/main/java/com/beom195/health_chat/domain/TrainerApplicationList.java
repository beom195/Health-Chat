package com.beom195.health_chat.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Table(name = "Trainerapplicationlist")
public class TrainerApplicationList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_list_id")
    private Long trainerListId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public TrainerApplicationList(Long trainerListId, Member member, Status status) {
        this.trainerListId = trainerListId;
        this.member = member;
        this.status = status;
    }
}

