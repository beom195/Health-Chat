package com.beom195.health_chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {

    // 관리자 전용 페이지로 이동
    @GetMapping("/admin/adminPage")
    public String adminPage(){
        return "admin/adminPage";
    }
}
