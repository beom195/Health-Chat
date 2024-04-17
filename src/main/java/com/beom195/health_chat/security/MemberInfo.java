package com.beom195.health_chat.security;

import com.beom195.health_chat.domain.Member;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;


@Getter
public class MemberInfo extends User {

    /*
    로그인한 member 객체 정보를 MemberDetailService에서 MemberInfo가 반환
     */
    private final Member member;

    public MemberInfo(Member member){
        super(member.getMemberLoginId(), member.getMemberPassword(), List.of(new SimpleGrantedAuthority(member.getRole().getValue())));
        this.member = member;

    }
}
