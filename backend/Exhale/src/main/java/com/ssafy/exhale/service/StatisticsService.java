package com.ssafy.exhale.service;

import com.ssafy.exhale.dto.responseDto.statisticsDto.LetterStatisticsResponse;
import com.ssafy.exhale.repository.rehabilitationRepository.LetterRecodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatisticsService {
    private final LetterRecodeRepository letterRecodeRepository;

    public List<LetterStatisticsResponse> getLetterData(Long memberId) {
        return letterRecodeRepository.getLetterRecode(memberId, PageRequest.of(0, 5));
    }
}
