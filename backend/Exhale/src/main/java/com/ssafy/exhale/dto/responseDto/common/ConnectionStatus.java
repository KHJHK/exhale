package com.ssafy.exhale.dto.responseDto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConnectionStatus {
    private int httpCode;
    private String message;
}

