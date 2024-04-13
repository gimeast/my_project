package com.project.memo.repository;

import com.project.memo.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberIdAndPassword(String memberId, String password);
}
