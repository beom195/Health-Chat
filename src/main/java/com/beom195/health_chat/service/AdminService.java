package com.beom195.health_chat.service;

import com.beom195.health_chat.dto.MemberDTO;
import com.beom195.health_chat.dto.TrainerApplicationListDTO;

import java.util.List;

public interface AdminService {
    List<TrainerApplicationListDTO> getTrainerApplicationList();

    void trainerAccept(Long acceptMemberId, MemberDTO.Request memberDTO);

    void trainerReject(Long rejectMemberId);
}
