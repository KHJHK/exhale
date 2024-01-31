package com.ssafy.exhale.repository;

import com.ssafy.exhale.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Boolean existsByLoginId(String login_id);
    Member findByLoginId(String login_id);

    Optional<Member> findById(long member_id);

    Boolean existsByIdAndRefreshValue(long memberId, String tokenValue);
}
