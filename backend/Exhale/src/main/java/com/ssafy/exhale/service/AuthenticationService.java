package com.ssafy.exhale.service;

import com.ssafy.exhale.domain.Authentication;
import com.ssafy.exhale.repository.AuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationRepository authenticationRepository;

    @Value("${Spring.jwt.refresh-token}")
    private long expireAt;

    public boolean saveRefreshValue(Long memberId, String refreshValue) {
        removeRefreshValue(memberId);

        Authentication authentication = Authentication.of(memberId, refreshValue, expireAt);

        authenticationRepository.save(authentication);
        // todo 예외 처리
        return true;
    }

    public void reSaveRefreshValue(Long memberId, String refreshValue) {
        removeRefreshValue(memberId);
        saveRefreshValue(memberId, refreshValue);

    }

    public boolean compareRefreshToken(Long memberId, String tokenValue) {
        Optional<Authentication> authentication = authenticationRepository.findByMemberId(memberId);
        if (authentication.get().getRefreshValue().equals(tokenValue)) return true;
        else return false;
    }

    public void removeRefreshValue(long memberId) {
        List<Authentication> authentications = authenticationRepository.findAllByMemberId(memberId);
        for (Authentication auth : authentications) {
            authenticationRepository.deleteByMemberId(auth.getMemberId());
        }
    }
    public void logout(long memberId){
        authenticationRepository.findByMemberId(memberId).ifPresent((auth)->{
            authenticationRepository.deleteByMemberId(memberId);
        });
    }
}