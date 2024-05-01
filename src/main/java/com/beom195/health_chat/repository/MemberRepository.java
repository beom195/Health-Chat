package com.beom195.health_chat.repository;

import com.beom195.health_chat.domain.Member;
import com.beom195.health_chat.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberLoginId(String memberLoginId);

    List<Member> findByRole(Role role);
}
