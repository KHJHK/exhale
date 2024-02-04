package com.ssafy.exhale.domain;

import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "certification_code")
public class CertificationCode {
    private String id;
    @Field("member_id")
    private Long memberId;
    private String code;
    @Field("create_at")
    private LocalDateTime createAt;

    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
    }


    public static CertificationCode of(){
        return new CertificationCode();
    }
    public static CertificationCode of(String id, Long memberId, String code, LocalDateTime createAt){
        return new CertificationCode(id, memberId, code, createAt);
    }
}
