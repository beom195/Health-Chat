package com.beom195.health_chat.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "member_login_id")
    private String memberLoginId;

    @Column(name = "member_password")
    private String memberPassword;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "member_email")
    private String memberEmail;

    @Enumerated(EnumType.STRING)
    private Role role;

    //트레이너 신청 수락시 role MEMBER -> TRAINER
    public void roleUpdate(Role role){
        this.role = role.TRAINER;
    }

    @Builder
    public Member(Long memberId, String memberLoginId, String memberPassword, String memberName, String memberEmail, Role role) {
        this.memberId = memberId;
        this.memberLoginId = memberLoginId;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.role = role;
    }
}
