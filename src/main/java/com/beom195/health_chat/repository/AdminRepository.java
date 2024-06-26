package com.beom195.health_chat.repository;

import com.beom195.health_chat.domain.TrainerApplicationList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<TrainerApplicationList, Long> {

    /*
    관리자 페이지에서 신청자 목록 불러올시 member table N+1 문제
    fetch join 방식으로 TrainerApplicationList Entity, Member Entity 한번에 조회(내림차순)
     */
    @Query("SELECT trainerApplicationList FROM TrainerApplicationList trainerApplicationList JOIN FETCH trainerApplicationList.member ORDER BY trainerApplicationList.trainerListId DESC")
    List<TrainerApplicationList> findAllWithMember();

    Optional<TrainerApplicationList> findByMemberMemberId(Long acceptMemberId);
}
