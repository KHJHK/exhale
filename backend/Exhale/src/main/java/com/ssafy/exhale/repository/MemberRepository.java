package com.ssafy.exhale.repository;

import com.ssafy.exhale.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByLoginId(String login_id);
}
