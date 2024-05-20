package com.beom195.health_chat.service;

import com.beom195.health_chat.domain.Member;
import com.beom195.health_chat.domain.Review;
import com.beom195.health_chat.domain.Role;
import com.beom195.health_chat.domain.Trainer;
import com.beom195.health_chat.dto.ReviewDTO;
import com.beom195.health_chat.dto.TrainerDTO;
import com.beom195.health_chat.repository.ReviewRepository;
import com.beom195.health_chat.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainerServiceImpl implements TrainerService{

    private final TrainerRepository trainerRepository;
    private final ReviewRepository reviewRepository;

    //트레이너 찾기 -> 트레이너 리스트 조회
    @Transactional
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
    @Transactional
    @Override
    public TrainerDTO getTrainerProfile(String trainerLoginId) {
        Trainer findTrainer = trainerRepository.findByTrainerLoginId(trainerLoginId).orElseThrow(() -> new IllegalArgumentException("해당 트레이너를 찾을 수 없습니다."));
        return TrainerDTO.builder()
                .trainerId(findTrainer.getTrainerId())
                .trainerLoginId(findTrainer.getTrainerLoginId())
                .trainerName(findTrainer.getTrainerName())
                .trainerEmail(findTrainer.getTrainerEmail())
                .role(Role.TRAINER)
                .likes(0)
                .build();
    }

    //트레이너 리뷰 작성
    @Transactional
    @Override
    public void submitTrainerReview(Member member, String trainerLoginId, String reviewContent) {

        Trainer trainer = trainerRepository.findByTrainerLoginId(trainerLoginId).orElseThrow(() -> new IllegalArgumentException("해당 트레이너를 찾을 수 없습니다."));
        reviewRepository.save(Review.builder().member(member).trainer(trainer).reviewContent(reviewContent).build());
    }

    //트레이너 리뷰 조회
    @Transactional
    @Override
    public List<ReviewDTO> getTrainerReview(String trainerLoginId) {

        List<Review> trainerReviewList = reviewRepository.findReviewByTrainerTrainerLoginId(trainerLoginId);
        return trainerReviewList.stream().map((review -> ReviewDTO.builder()
                .reviewId(review.getReviewId())
                .member(review.getMember())
                .trainer(review.getTrainer())
                .reviewContent(review.getReviewContent())
                .createdDate(review.getCreatedDate())
                .modifiedDate(review.getModifiedDate())
                .build()))
                .collect(Collectors.toList());
    }
}
