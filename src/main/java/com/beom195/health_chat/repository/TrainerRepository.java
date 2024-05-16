package com.beom195.health_chat.repository;

import com.beom195.health_chat.domain.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    Optional<Trainer> findByTrainerLoginId(String trainerLoginId);
}
