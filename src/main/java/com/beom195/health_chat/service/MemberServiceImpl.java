package com.beom195.health_chat.service;

import com.beom195.health_chat.domain.Member;
import com.beom195.health_chat.domain.Role;
import com.beom195.health_chat.domain.TrainerApplicationList;
import com.beom195.health_chat.dto.MemberDTO;
import com.beom195.health_chat.repository.MemberRepository;
import com.beom195.health_chat.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    @Transactional
    @Override
    public void memberSave(MemberDTO.Request memberDTO) {

        String encodedPassword = passwordEncoder.encode(memberDTO.getMemberPassword());

        memberRepository.save(Member.builder().memberLoginId(memberDTO.getMemberLoginId()).memberPassword(encodedPassword).memberName(memberDTO.getMemberName()).memberEmail(memberDTO.getMemberEmail()).role(Role.MEMBER).build());
    }

    //마이페이지에서 트레이너 신청
    @Transactional
    @Override
    public void requestTrainer(String memberLoginId) {

        Member member = memberRepository.findByMemberLoginId(memberLoginId).orElseThrow(() -> new UsernameNotFoundException("해당하는 사용자를 찾지 못했습니다"));

        log.info("requestTrainer - member: {}", member.toString());
        TrainerApplicationList trainerApplicationList = TrainerApplicationList.builder().member(member).build();
        adminRepository.save(trainerApplicationList);
        log.info("trainerApplicationList = {}",  trainerApplicationList);
    }

    // 트레이너 찾기 -> 리스트로 트레이너 목록 불러옴
    @Transactional
    @Override
    public List<MemberDTO.Response> findTrainer() {

        List<Member> trainerList = memberRepository.findByRole(Role.TRAINER);
        return trainerList.stream()
                .map(member -> MemberDTO.Response.builder()
                        .memberId(member.getMemberId())
                        .memberLoginId(member.getMemberLoginId())
                        .memberName(member.getMemberName())
                        .memberEmail(member.getMemberEmail())
                        .role(member.getRole())
                        .build())
                .collect(Collectors.toList());
    }
}
