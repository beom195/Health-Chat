package com.beom195.health_chat.dto;

import com.beom195.health_chat.domain.Member;
import com.beom195.health_chat.domain.Role;
import lombok.*;

@Getter
public class MemberDTO {

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @Builder
    public static class Request {

        private Long memberId;
        private String memberLoginId;
        private String memberPassword;
        private String memberName;
        private String memberEmail;
        private Role role;

        // DTO -> Entity
        public Member toEntity(){
            return Member.builder()
                    .memberId(memberId)
                    .memberLoginId(memberLoginId)
                    .memberPassword(memberPassword)
                    .memberName(memberName)
                    .memberEmail(memberEmail)
                    .role(Role.MEMBER)
                    .build();
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @Builder
    public static class Response{

        private Long memberId;
        private String memberLoginId;
        private String memberPassword;
        private String memberName;
        private String memberEmail;
        private Role role;

        // Entity -> DTO
        public Response(Member member){
            this.memberId = member.getMemberId();
            this.memberLoginId = member.getMemberLoginId();
            this.memberPassword = member.getMemberPassword();
            this.memberName = member.getMemberName();
            this.memberEmail = member.getMemberEmail();
            this.role = member.getRole();
        }
    }
}
