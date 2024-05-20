package com.beom195.health_chat.controller;

import com.beom195.health_chat.domain.Member;
import com.beom195.health_chat.dto.ReviewDTO;
import com.beom195.health_chat.dto.TrainerDTO;
import com.beom195.health_chat.security.AuthMember;
import com.beom195.health_chat.service.TrainerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    //트레이너 찾기 페이지로 이동
    @GetMapping("/member/findTrainer")
    public String findTrainerPage(Model model){

        List<TrainerDTO> trainerList = trainerService.findTrainer();
        model.addAttribute("trainerList", trainerList);

        return "trainer/trainerSearch";
    }

    //트레이너 상세 페이지 + 리뷰 조회 및 작성
    @GetMapping("/member/findTrainer/profile")
    public String getTrainerProfile(@RequestParam String trainerLoginId, Model model){

        TrainerDTO trainer = trainerService.getTrainerProfile(trainerLoginId);

        List<ReviewDTO> trainerReview = trainerService.getTrainerReview(trainerLoginId);
        log.info("trainerReview = {}", trainerReview);
        model.addAttribute("reviewList", trainerReview);
        model.addAttribute("trainer", trainer);

        return "trainer/trainerProfile";
    }

    //트레이너 리뷰 작성
    @PostMapping("/trainer/submitReview")
    public String trainerReviewWrite(@AuthMember Member member, String trainerLoginId, String reviewContent){

        trainerService.submitTrainerReview(member, trainerLoginId, reviewContent);

        return "redirect:/member/findTrainer/profile?trainerLoginId=" + trainerLoginId;
    }
}
