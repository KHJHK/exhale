package com.ssafy.exhale.repository;

import com.ssafy.exhale.domain.Authentication;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AuthenticationRepository extends MongoRepository <Authentication,String>{
    List<Authentication> findAllByMemberId(long memberId);
    Optional<Authentication> findByMemberId(long memberId);
    long deleteByMemberId(long memberId);
}
