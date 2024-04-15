package com.beom195.health_chat.dto;

import com.beom195.health_chat.domain.Member;
import com.beom195.health_chat.domain.Role;
import com.beom195.health_chat.domain.Trainer;
import lombok.*;

@Getter
public class TrainerDTO {

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @Builder
    public static class Request {

        private Long trainerId;
        private String trainerLoginId;
        private String trainerPassword;
        private String trainerName;
        private String trainerEmail;
        private Role role;

        // DTO -> Entity
        public Trainer toEntity(){
            return Trainer.builder()
                    .trainerId(trainerId)
                    .trainerLoginId(trainerLoginId)
                    .trainerPassword(trainerPassword)
                    .trainerName(trainerName)
                    .trainerEmail(trainerEmail)
                    .role(Role.TRAINER)
                    .build();
        }

        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        @Getter
        @Builder
        public static class Response{

            private Long trainerId;
            private String trainerLoginId;
            private String trainerPassword;
            private String trainerName;
            private String trainerEmail;
            private Role role;

            // Entity -> DTO
            public Response(Trainer trainer){
                this.trainerId = trainer.getTrainerId();
                this.trainerLoginId = trainer.getTrainerLoginId();
                this.trainerPassword = trainer.getTrainerPassword();
                this.trainerName = trainer.getTrainerName();
                this.trainerEmail = trainer.getTrainerEmail();
                this.role = trainer.getRole();
            }
        }
    }
}
