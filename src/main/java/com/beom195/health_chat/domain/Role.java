package com.beom195.health_chat.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//권한 부여를 위해 생성
@Getter
@RequiredArgsConstructor
public enum Role {

    MEMBER("ROLE_MEMBER"),
    TRAINER("ROLE_TRAINER");

    private final String value;
}