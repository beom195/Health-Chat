package com.beom195.health_chat.service;


import com.beom195.health_chat.dto.MemberDTO;

public interface MemberService {

    void memberSave(MemberDTO.Request memberDTO);
}
