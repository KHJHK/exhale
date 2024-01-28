package com.ssafy.exhale.repository;

import com.ssafy.exhale.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Boolean existsByLoginId(String login_id);
    Member findByLoginId(String login_id);

    Optional<Member> findByMemberId(int member_id);

    Boolean existsByMemberIdAndRefreshValue(int memberId, String tokenValue);
}
