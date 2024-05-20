package com.beom195.health_chat.repository;

import com.beom195.health_chat.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    //연관된 Entity 한번에 조회하기 위해 fetch join 사용
    @Query("SELECT r FROM Review r JOIN FETCH r.member JOIN FETCH r.trainer WHERE r.trainer.trainerLoginId = :trainerLoginId")
    List<Review> findReviewByTrainerTrainerLoginId(String trainerLoginId);
}
