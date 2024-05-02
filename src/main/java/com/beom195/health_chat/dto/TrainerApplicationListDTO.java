package com.beom195.health_chat.dto;

import com.beom195.health_chat.domain.Member;
import com.beom195.health_chat.domain.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TrainerApplicationListDTO {

    private Long trainerListId;

    private Member member;

    private Status status;

    @Builder
    public TrainerApplicationListDTO(Long trainerListId, Member member, Status status) {
        this.trainerListId = trainerListId;
        this.member = member;
        this.status = status;
    }
}
