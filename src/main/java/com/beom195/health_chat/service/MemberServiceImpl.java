package com.beom195.health_chat.service;

import com.beom195.health_chat.domain.*;
import com.beom195.health_chat.dto.MemberDTO;
import com.beom195.health_chat.dto.ReviewDTO;
import com.beom195.health_chat.repository.AdminRepository;
import com.beom195.health_chat.repository.MemberRepository;
import com.beom195.health_chat.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final AdminRepository adminRepository;
    private final ReviewRepository reviewRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    @Transactional
    @Override
    public void memberSave(MemberDTO.Request memberDTO) {

        String encodedPassword = passwordEncoder.encode(memberDTO.getMemberPassword());

        memberRepository.save(Member.builder().memberLoginId(memberDTO.getMemberLoginId()).memberPassword(encodedPassword).memberName(memberDTO.getMemberName()).memberEmail(memberDTO.getMemberEmail()).role(Role.MEMBER).build());
    }

    //마이페이지에서 트레이너 신청
    @Transactional
    @Override
    public void requestTrainer(String memberLoginId) {

        Member member = memberRepository.findByMemberLoginId(memberLoginId).orElseThrow(() -> new UsernameNotFoundException("해당하는 사용자를 찾지 못했습니다"));

        log.info("requestTrainer - member: {}", member.toString());
        //트레이너 신청 현황에서 대기중으로 변경
        TrainerApplicationList trainerApplicationList = TrainerApplicationList.builder()
                .member(member)
                .status(Status.PENDING)
                .build();
        adminRepository.save(trainerApplicationList);
        log.info("trainerApplicationList = {}",  trainerApplicationList);
    }

    //본인이 작성한 리뷰 마이페이지에서 조회
    @Transactional
    @Override
    public List<ReviewDTO> viewMyReview(Long memberId) {

        List<Review> reviewList = reviewRepository.findReviewByMemberMemberId(memberId);

        if (reviewList.isEmpty()) {
            throw new IllegalArgumentException("해당 리뷰를 찾지 못했습니다.");
        }

        return reviewList.stream().map((review -> ReviewDTO.builder()
                .reviewId(review.getReviewId())
                .member(review.getMember())
                .trainer(review.getTrainer())
                .reviewContent(review.getReviewContent())
                .createdDate(review.getCreatedDate())
                .modifiedDate(review.getModifiedDate())
                .build()))
                .collect(Collectors.toList());
    }
}
