package com.ssafy.exhale.repository.rehabilitationRepository;

import com.ssafy.exhale.domain.LetterRecode;
import com.ssafy.exhale.dto.responseDto.rehabilitationDto.LetterResponse;
import com.ssafy.exhale.dto.responseDto.statisticsDto.LetterStatisticsResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LetterRecodeRepository extends JpaRepository<LetterRecode ,Long> {

    @Query(value = "select new com.ssafy.exhale.dto.responseDto.statisticsDto.LetterStatisticsResponse(lr.letter.content, sum(lr.correctCnt + lr.wrongCnt), sum(lr.correctCnt), sum(lr.wrongCnt)) from LetterRecode lr " +
            "join lr.letter l " +
            "join lr.member m " +
            "where m.id = :memberId " +
            "group by lr.letter " +
            "order by sum(lr.wrongCnt) desc")
    List<LetterStatisticsResponse> getLetterRecode(@Param("memberId") long memberId, Pageable pageable);
}
