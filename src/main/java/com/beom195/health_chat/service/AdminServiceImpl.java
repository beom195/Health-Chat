package com.beom195.health_chat.service;


import com.beom195.health_chat.domain.Member;
import com.beom195.health_chat.domain.Role;
import com.beom195.health_chat.domain.TrainerApplicationList;
import com.beom195.health_chat.dto.MemberDTO;
import com.beom195.health_chat.dto.TrainerApplicationListDTO;
import com.beom195.health_chat.repository.AdminRepository;
import com.beom195.health_chat.repository.MemberRepository;
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

    // 트레이너 신청자 목록 관리자 페이지로 가져오기
    @Transactional
    @Override
    public List<TrainerApplicationListDTO> getTrainerApplicationList() {
        List<TrainerApplicationList> lists = adminRepository.findAllWithMember();

        return lists.stream()
                .map(trainerApplicationList -> TrainerApplicationListDTO.builder()
                        .trainerListId(trainerApplicationList.getTrainerListId())
                        .member(trainerApplicationList.getMember())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void trainerAccept(Long memberId, MemberDTO.Request memberDTO) {
        Member trainerApplicant = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 신청자를 찾을 수 없습니다."));
        log.info(trainerApplicant.getRole().toString());
        trainerApplicant.roleUpdate(Role.TRAINER);
        log.info(trainerApplicant.getRole().toString());
    }

    // 관리자 페이지에서 트레이너 신청 받아주기
}
