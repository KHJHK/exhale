package com.ssafy.exhale.repository;

import com.ssafy.exhale.domain.CertificationCode;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CertificationCodeRepository extends MongoRepository<CertificationCode, String> {

}
