package com.beom195.health_chat.service;

import com.beom195.health_chat.domain.Member;
import com.beom195.health_chat.domain.Role;
import com.beom195.health_chat.domain.TrainerApplicationList;
import com.beom195.health_chat.dto.MemberDTO;
import com.beom195.health_chat.repository.MemberRepository;
import com.beom195.health_chat.repository.TrainerApplicationListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final TrainerApplicationListRepository trainerApplicationListRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void memberSave(MemberDTO.Request memberDTO) {

        String encodedPassword = passwordEncoder.encode(memberDTO.getMemberPassword());

        memberRepository.save(Member.builder().memberLoginId(memberDTO.getMemberLoginId()).memberPassword(encodedPassword).memberName(memberDTO.getMemberName()).memberEmail(memberDTO.getMemberEmail()).role(Role.MEMBER).build());
    }

    @Transactional
    @Override
    public void requestTrainer(String memberLoginId) {

        Member member = memberRepository.findByMemberLoginId(memberLoginId).orElseThrow(() -> new UsernameNotFoundException("해당하는 사용자를 찾지 못했습니다 = " + memberLoginId));

        log.info("requestTrainer - member: {}", member.toString());
        TrainerApplicationList trainerApplicationList = TrainerApplicationList.builder()
                .member(member).build();
        log.info("trainerApplicationList = {}", trainerApplicationList.toString());
        trainerApplicationListRepository.save(trainerApplicationList);
    }
}
