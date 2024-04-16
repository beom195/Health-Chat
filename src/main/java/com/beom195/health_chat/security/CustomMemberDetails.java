package com.beom195.health_chat.security;

import com.beom195.health_chat.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//사용자의 정보를 담기 위해 CustomUserDetails 생성
@RequiredArgsConstructor
@Slf4j
public class CustomMemberDetails implements UserDetails {

    private final Member member;

    // 사용자의 권한 목록을 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        log.info("role = {}", member.getRole());
        authorities.add(() -> "ROLE_" + member.getRole());
        return authorities;
    }

    // 사용자 비밀번호 반환
    @Override
    public String getPassword() {
        return member.getMemberPassword();
    }

    // 사용자 이름 반환
    @Override
    public String getUsername() {
        return member.getMemberName();
    }

    // 사용자 계정의 만료 여부를 반환
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 사용자 계정이 잠겨 있는지 여부를 반환
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 사용자 자격 증명의 만료 여부를 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 사용자 계정의 활성화 여부를 반환
    @Override
    public boolean isEnabled() {
        return true;
    }
}
