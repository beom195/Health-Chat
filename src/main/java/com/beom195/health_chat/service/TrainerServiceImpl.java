package com.beom195.health_chat.service;

import com.beom195.health_chat.domain.Role;
import com.beom195.health_chat.domain.Trainer;
import com.beom195.health_chat.dto.TrainerDTO;
import com.beom195.health_chat.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainerServiceImpl implements TrainerService{

    private final TrainerRepository trainerRepository;

    //트레이너 찾기 -> 트레이너 리스트 조회
    @Override
    public List<TrainerDTO> findTrainer() {

        List<Trainer> trainerList = trainerRepository.findAll();
        return trainerList.stream()
                .map(trainer -> TrainerDTO.builder()
                        .trainerId(trainer.getTrainerId())
                        .trainerLoginId(trainer.getTrainerLoginId())
                        .trainerName(trainer.getTrainerName())
                        .trainerEmail(trainer.getTrainerEmail())
                        .role(Role.TRAINER)
                        .likes(0)
                        .build())
                .collect(Collectors.toList());
    }

    //트레이너 상세 페이지
    @Override
    public TrainerDTO getTrainerProfile(String trainerName) {
        Trainer findTrainer = trainerRepository.findByTrainerName(trainerName).orElseThrow(() -> new IllegalArgumentException("해당 트레이너를 찾을 수 없습니다."));
        return TrainerDTO.builder()
                .trainerId(findTrainer.getTrainerId())
                .trainerLoginId(findTrainer.getTrainerLoginId())
                .trainerName(findTrainer.getTrainerName())
                .trainerEmail(findTrainer.getTrainerEmail())
                .role(Role.TRAINER)
                .likes(0)
                .build();
    }


}
