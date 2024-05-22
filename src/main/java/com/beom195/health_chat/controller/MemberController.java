package com.beom195.health_chat.controller;

import com.beom195.health_chat.domain.Member;
import com.beom195.health_chat.dto.MemberDTO;
import com.beom195.health_chat.dto.ReviewDTO;
import com.beom195.health_chat.security.AuthMember;
import com.beom195.health_chat.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 멤버 회원가입 폼으로 이동
    @GetMapping("/member/join")
    public String join(){

        log.info("회원가입 이동");

        return "member/joinPage";
    }

    // 멤버 회원가입(기능구현 후 ajax로 바꾸기)
    @PostMapping("/memberJoin_proc")
    public String memberJoin(MemberDTO.Request memberDTO){

        log.info("memberDTO = {}", memberDTO.getMemberPassword());
        memberService.memberSave(memberDTO);

        return "redirect:/";
    }

    // 멤버 로그인 폼으로 이동
    @GetMapping("/auth/memberLogin")
    public String memberlogin(){

        log.info("로그인 이동");

        return "member/loginPage";
    }

    // 마이 페이지로 이동
    @GetMapping("/member/myPage")
    public String getMyPage(@AuthMember Member member, Model model){

        //회원 정보 조회
        log.info("memberName = {}", member.getMemberName());
        model.addAttribute("currentMember", member);

        //마이페이지에서 작성한 리뷰 조회
        List<ReviewDTO> myReview = memberService.viewMyReview(member.getMemberId());
        model.addAttribute("myReview" , myReview);

        return "member/myPage";
    }

    // 트레이너 신청
    @PostMapping("/request_proc")
    public String trainerRequest(@AuthMember Member member){

        log.info("trainerRequest = {}", member.getMemberLoginId());
        memberService.requestTrainer(member.getMemberLoginId());

        return "redirect:/";
    }

    @GetMapping("/member/calendar")
    public String getCalendar(){
        return "member/calendar";
    }

}
