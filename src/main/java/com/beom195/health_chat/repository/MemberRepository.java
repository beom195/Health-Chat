package com.beom195.health_chat.repository;

import com.beom195.health_chat.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberLoginId(String memberLoginId);

}
