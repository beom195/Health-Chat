package com.beom195.health_chat.dto;

import com.beom195.health_chat.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TrainerApplicationListDTO {

    private Long trainerListId;

    private Member member;

    @Builder
    public TrainerApplicationListDTO(Long trainerListId, Member member) {
        this.trainerListId = trainerListId;
        this.member = member;
    }
}
