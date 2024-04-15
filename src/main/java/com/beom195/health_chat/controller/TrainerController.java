package com.beom195.health_chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TrainerController {

    // 트레이너 회원가입 폼으로 이동
    @GetMapping("/trainer/join")
    public String join(){
        return "trainer/joinPage";
    }

}
