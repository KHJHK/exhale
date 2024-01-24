package com.ssafy.exhale.repository;

import com.ssafy.exhale.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Boolean existsByLoginId(String login_id);
    Member findByLoginId(String login_id);
}
