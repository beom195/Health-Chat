package com.beom195.health_chat.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

/*
  사용자가 비회원일시 anonymousUser로 간주하여 null 반환
  사용자가 회원일시 null이 아니라 member 객체 주입
  @AuthenticationPrincipal  현재 사용자를 주입받기 위해 사용
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : member")
public @interface AuthMember {
}
