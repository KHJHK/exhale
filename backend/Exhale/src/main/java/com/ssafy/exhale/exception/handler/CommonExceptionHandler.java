package com.ssafy.exhale.exception.handler;

import com.ssafy.exhale.dto.responseDto.commonDto.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

    //json 변환 실패 예외
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> httpMessageNotReadableExceptionHandle() {
        return CommonResponse.connectionError(HttpStatus.BAD_REQUEST, "invalid json format");
    }



}
