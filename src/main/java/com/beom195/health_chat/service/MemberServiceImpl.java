package com.beom195.health_chat.service;

import com.beom195.health_chat.domain.Member;
import com.beom195.health_chat.domain.Role;
import com.beom195.health_chat.dto.MemberDTO;
import com.beom195.health_chat.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void memberSave(MemberDTO.Request memberDTO) {

        String encodedPassword = passwordEncoder.encode(memberDTO.getMemberPassword());

        memberRepository.save(Member.builder()
                .memberLoginId(memberDTO.getMemberLoginId())
                .memberPassword(encodedPassword)
                .memberName(memberDTO.getMemberName())
                .memberEmail(memberDTO.getMemberEmail())
                .role(Role.MEMBER)
                .build());
    }
}
