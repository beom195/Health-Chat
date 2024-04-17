package com.beom195.health_chat.security;

import com.beom195.health_chat.domain.Member;
import com.beom195.health_chat.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


//Spring Security에서 사용자의 정보를 가져오기 위해 MemberDetailService 생성
@Service
@RequiredArgsConstructor
@Slf4j
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // memberLoginId로 DB에 접근해 확인
    @Override
    public UserDetails loadUserByUsername(String memberLoginId) throws UsernameNotFoundException {

        //로그인 id를 조회
        Member member = memberRepository.findByMemberLoginId(memberLoginId).orElseThrow(() -> new UsernameNotFoundException("해당하는 사용자를 찾지 못했습니다 = " + memberLoginId));

        log.info("loginId = {}", member.getMemberLoginId());
        log.info("loginMemberRole = {}", member.getRole());

        return new MemberInfo(member);
    }
}
