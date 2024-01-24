package com.ssafy.exhale.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.exhale.dto.logicDto.CustomUserDetails;
import com.ssafy.exhale.dto.responseDto.MemberResponse;
import com.ssafy.exhale.dto.responseDto.TokenInfo;
import com.ssafy.exhale.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RequiredArgsConstructor

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private String usernameParameter = "login_id";

    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtil;

    private final ObjectMapper objectMapper;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {


        String login_id = obtainUsername(request);
        String password = obtainPassword(request);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login_id, password, null); // JWT를 사용할 것임으로 3번째 인자를 사용하지 않을 거임

        return authenticationManager.authenticate(authToken);
    }
    @Override
    @Nullable
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(this.usernameParameter);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String loginId = customUserDetails.getUsername();
        int memberId = customUserDetails.getMemberId();
        String nickname = customUserDetails.getNickname();

        //roll 값
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        String jwt = jwtUtil.createJwt(loginId, memberId, role);


        TokenInfo tokeninfo = new TokenInfo("Bearer "+jwt,null);
        MemberResponse memberResponse = new MemberResponse(memberId, loginId, nickname);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Map<String, Object> responseBody = new HashMap<>();

        responseBody.put("token",tokeninfo);
        responseBody.put("member",memberResponse);


        try{
            String jsonResponse = objectMapper.writeValueAsString(responseBody);
            response.getWriter().write(jsonResponse);

        }catch (IOException e){
            logger.error("Error while constructing and writing JSON response", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

//        response.addHeader("Authorization", "Bearer "+jwt);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {

        response.setStatus(401);
    }
}
