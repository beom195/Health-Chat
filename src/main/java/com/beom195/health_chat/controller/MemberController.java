package com.beom195.health_chat.controller;

import com.beom195.health_chat.dto.MemberDTO;
import com.beom195.health_chat.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원가입 폼으로 이동
    @GetMapping("/member/join")
    public String join(){
        return "member/joinPage";
    }

    //회원가입(기능구현 후 ajax로 바꾸기)
    @PostMapping("/memberJoin_proc")
    public String memberJoin(MemberDTO.Request memberDTO){

        log.info("memberDTO = {}", memberDTO.getMemberPassword());
        memberService.memberSave(memberDTO);
        return "member/success";
    }

    //로그인 폼으로 이동
    @GetMapping("/member/login")
    public String login(){
        return "member/loginPage";
    }
}
