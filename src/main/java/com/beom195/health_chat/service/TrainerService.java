package com.beom195.health_chat.service;

import com.beom195.health_chat.domain.Trainer;
import com.beom195.health_chat.dto.TrainerDTO;

import java.util.List;

public interface TrainerService {
    TrainerDTO getTrainerProfile(String trainerName);

    List<TrainerDTO> findTrainer();
}
