package com.ssafy.exhale.util;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {
    private SecretKey secretKey;

    public JWTUtil( @Value("${spring.jwt.secret}") String secret){
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());

    }

    // JWT 검증
    public String getLoginId(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("login_id",String.class);
    }

    public String getRole(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public String getName(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("name",String.class);
    }

    public int getUserId(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("user_id",Integer.class);
    }
    public String getNickname(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("nickname",String.class);
    }

    public Boolean isExpired(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }


    // JWT 생성
    public String createJwt(String name, String loginId, int userId, String nickname, String role, Long expiredMs){
        return Jwts.builder()
                .claim("name",name)
                .claim("login_id",loginId)
                .claim("user_id",userId)
                .claim("nickname", nickname)
                .claim("role",role)
                .issuedAt(new Date(System.currentTimeMillis()))   // 토큰 발행 시간
                .expiration(new Date(System.currentTimeMillis() + expiredMs)) // 토큰 소멸시간
                .signWith(secretKey)
                .compact();
    }
}
