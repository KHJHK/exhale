package com.ssafy.exhale.dto.responseDto.rehabilitationDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.exhale.domain.Letter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LetterResponse {
    @JsonProperty("letter_id")
    private Long letterId;

    @JsonProperty("letter_char")
    private String letterChar;

    public static LetterResponse of(Letter letter) {
        return new LetterResponse(letter.getId(), letter.getCharacter());
    }
}