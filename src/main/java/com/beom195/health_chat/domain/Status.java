package com.beom195.health_chat.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {

    PENDING("대기 중"),
    ACCEPTED("수락됨"),
    REJECTED("거절됨");

    private final String value;
}
