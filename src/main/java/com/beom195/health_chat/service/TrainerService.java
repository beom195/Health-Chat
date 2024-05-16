package com.beom195.health_chat.service;

import com.beom195.health_chat.domain.Member;
import com.beom195.health_chat.dto.ReviewDTO;
import com.beom195.health_chat.dto.TrainerDTO;

import java.util.List;

public interface TrainerService {
    TrainerDTO getTrainerProfile(String trainerLoginId);

    List<TrainerDTO> findTrainer();

    void submitTrainerReview(Member member, String trainerLoginId, String reviewContent);
}
