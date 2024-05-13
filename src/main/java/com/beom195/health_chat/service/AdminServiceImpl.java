package com.beom195.health_chat.service;


import com.beom195.health_chat.domain.*;
import com.beom195.health_chat.dto.MemberDTO;
import com.beom195.health_chat.dto.TrainerApplicationListDTO;
import com.beom195.health_chat.repository.AdminRepository;
import com.beom195.health_chat.repository.MemberRepository;
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
public class AdminServiceImpl implements AdminService {

    private final MemberRepository memberRepository;
    private final AdminRepository adminRepository;
    private final TrainerRepository trainerRepository;

    // 트레이너 신청자 목록 관리자 페이지로 가져오기
    @Transactional
    @Override
    public List<TrainerApplicationListDTO> getTrainerApplicationList() {
        List<TrainerApplicationList> lists = adminRepository.findAllWithMember();

        return lists.stream().map(trainerApplicationList -> TrainerApplicationListDTO.builder().trainerListId(trainerApplicationList.getTrainerListId()).member(trainerApplicationList.getMember()).status(trainerApplicationList.getStatus()).build()).collect(Collectors.toList());
    }

    // 트레이너 신청 수락 -> 신청 현황 "수락됨"으로 업데이트
    @Transactional
    @Override
    public void trainerAccept(Long acceptMemberId, MemberDTO.Request memberDTO) {

        Member trainerApplicant = memberRepository.findById(acceptMemberId).orElseThrow(() -> new IllegalArgumentException("해당 신청자를 찾을 수 없습니다."));

        TrainerApplicationList trainerApplicationList = adminRepository.findByMemberMemberId(acceptMemberId).orElseThrow(() -> new IllegalArgumentException("해당 신청자를 찾을 수 없습니다."));

        //트레이너 신청 수락 -> 트레이너 테이블에 member 정보를 저장
        trainerRepository.save(Trainer.builder().trainerLoginId(trainerApplicant.getMemberLoginId()).trainerName(trainerApplicant.getMemberName()).trainerEmail(trainerApplicant.getMemberEmail()).role(Role.TRAINER).likes(0).build());

        //트레이너 신청 현황 "수락됨"으로 업데이트
        trainerApplicationList.statusUpdate(Status.ACCEPTED);

        //트레이너 수락된 Member -> 기존 Member 테이블의 role을 MEMBER에서 TRAINER로 전환
        trainerApplicant.roleUpdate(Role.TRAINER);

        log.info("수락된 MemberName = {}, Member Role = {}", trainerApplicant.getMemberName(), trainerApplicant.getRole().toString());
    }

    // 트레이너 신청 거절 -> 신청 현황 "거절됨"으로 업데이트
    @Transactional
    @Override
    public void trainerReject(Long rejectMemberId) {

        //트레이너 신청 거절 -> 신청 현황 "거절됨"으로 업데이트
        TrainerApplicationList trainerApplicationList = adminRepository.findByMemberMemberId(rejectMemberId).orElseThrow(() -> new IllegalArgumentException("해당 신청자를 찾을 수 없습니다."));
        trainerApplicationList.statusUpdate(Status.REJECTED);
    }
}
