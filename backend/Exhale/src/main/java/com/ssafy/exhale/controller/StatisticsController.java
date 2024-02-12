package com.ssafy.exhale.controller;

import com.ssafy.exhale.dto.responseDto.statisticsDto.LetterStatisticsResponse;
import com.ssafy.exhale.service.StatisticsService;
import com.ssafy.exhale.util.TokenPayloadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final TokenPayloadUtil tokenPayloadUtil;

    @GetMapping("/letter")
    public List<LetterStatisticsResponse> getLetterData() {
        return statisticsService.getLetterData(tokenPayloadUtil.getMemberId());
    }
}
