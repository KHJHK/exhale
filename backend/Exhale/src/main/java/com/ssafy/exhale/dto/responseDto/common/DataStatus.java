package com.ssafy.exhale.dto.responseDto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DataStatus {
    private int code;
    private String message;
}
