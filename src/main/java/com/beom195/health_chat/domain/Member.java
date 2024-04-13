package com.beom195.health_chat.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long userId;

    @Column(name = "member_login_id")
    private String userLoginId;

    @Column(name = "member_password")
    private String userPassword;

    @Column(name = "member_name")
    private String userName;

    @Column(name = "member_email")
    private String userEmail;

}
