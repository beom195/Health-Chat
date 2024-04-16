package com.beom195.health_chat.controller;

import com.beom195.health_chat.dto.MemberDTO;
import com.beom195.health_chat.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 멤버 회원가입 폼으로 이동
    @GetMapping("/member/join")
    public String join(){
        return "member/joinPage";
    }

    // 어드민 전용 페이지로 이동
    @GetMapping("/admin/adminPage")
    public String adminPage(){
        return "admin/adminPage";
    }

    // 멤버 로그인 폼으로 이동
    @GetMapping("/auth/memberLogin")
    public String memberlogin(){
        return "member/loginPage";
    }

    // 마이 페이지로 이동
    @GetMapping("/member/myPage")
    public String getMyPage(){
        return "member/myPage";
    }

    // 멤버 회원가입(기능구현 후 ajax로 바꾸기)
    @PostMapping("/memberJoin_proc")
    public String memberJoin(MemberDTO.Request memberDTO){

        log.info("memberDTO = {}", memberDTO.getMemberPassword());
        memberService.memberSave(memberDTO);
        return "redirect:/";
    }
}
