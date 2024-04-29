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

    //트레이너 신청 수락
    @Transactional
    @Override
    public void trainerAccept(Long acceptMemberId, MemberDTO.Request memberDTO) {
        Member trainerApplicant = memberRepository.findById(acceptMemberId).orElseThrow(() -> new IllegalArgumentException("해당 신청자를 찾을 수 없습니다."));
        trainerApplicant.roleUpdate(Role.TRAINER);
        adminRepository.deleteByMemberMemberId(acceptMemberId);
        log.info(trainerApplicant.getRole().toString());
    }

    /*
    트레이너 신청 거절 -> 트레이너 신청 목록 테이블에서 해당 memberId 삭제
     */
    @Transactional
    @Override
    public void trainerReject(Long rejectMemberId) {
        adminRepository.deleteByMemberMemberId(rejectMemberId);
        log.info("삭제 완료!");
    }
}
