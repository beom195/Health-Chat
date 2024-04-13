package com.beom195.health_chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeContoller {

    //로그인 폼으로 이동
    @GetMapping("/member/login")
    public String login(){
        return "member/login";
    }
}
