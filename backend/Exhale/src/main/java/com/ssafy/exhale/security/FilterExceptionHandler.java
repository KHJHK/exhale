package com.ssafy.exhale.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.exhale.dto.responseDto.commonDto.CommonResponse;
import com.ssafy.exhale.util.MessageUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class FilterExceptionHandler extends OncePerRequestFilter {

    private MessageUtil messageUtil;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try{
            filterChain.doFilter(request, response);
        }catch (ExpiredJwtException e){
            //토큰의 유효기간 만료
            tokenExpireException(response, e);
        }catch (JwtException | IllegalArgumentException e){
            //유효하지 않은 토큰
            tokenInvalidException(response, e);
        }
    }
    private void tokenExpireException(
            HttpServletResponse response,
            Exception exception
    ){
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try{
            response.getWriter().write(objectMapper.writeValueAsString(CommonResponse.connectionError(HttpStatus.valueOf(401),"토큰 만료").getBody()));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void tokenInvalidException(
            HttpServletResponse response,
            Exception exception
    ) {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try{
            response.getWriter().write(objectMapper.writeValueAsString(CommonResponse.connectionError(HttpStatus.valueOf(401),"권한 없음").getBody()));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}