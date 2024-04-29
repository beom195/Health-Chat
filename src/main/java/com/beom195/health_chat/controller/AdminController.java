package com.beom195.health_chat.controller;

import com.beom195.health_chat.dto.MemberDTO;
import com.beom195.health_chat.dto.TrainerApplicationListDTO;
import com.beom195.health_chat.service.AdminService;
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
public class AdminController {

    private final AdminService adminService;

    // 관리자 전용 페이지로 이동
    @GetMapping("/admin/adminPage")
    public String adminPage(Model model){
        log.info("관리자 페이지로 이동");
        List<TrainerApplicationListDTO> trainerApplicationListDTO = adminService.getTrainerApplicationList();
        model.addAttribute("lists", trainerApplicationListDTO);
        return "admin/adminPage";
    }

    //트레이너 신청 수락
    @PostMapping("/accept_proc")
    public String trainerAccept(Long acceptMemberId, MemberDTO.Request memberDTO){
        log.info("memberId = {}", acceptMemberId);
        adminService.trainerAccept(acceptMemberId, memberDTO);

        return "redirect:/";
    }

    //트레이너 신청 거절
    @PostMapping("/reject_proc")
    public String trainerReject(Long rejectMemberId){
        log.info("memberId = {}", rejectMemberId);
        adminService.trainerReject(rejectMemberId);
        return "redirect:/";
    }
}
