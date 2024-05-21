package com.beom195.health_chat.service;


import com.beom195.health_chat.dto.MemberDTO;
import com.beom195.health_chat.dto.ReviewDTO;

import java.util.List;

public interface MemberService {

    void memberSave(MemberDTO.Request memberDTO);

    void requestTrainer(String memberLoginId);

    List<ReviewDTO> viewMyReview(Long memberId);
}
