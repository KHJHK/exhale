package com.ssafy.exhale.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.exhale.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private String usernameParameter = "login_id";
    private static final AntPathRequestMatcher CUSTOM_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/general/login", "POST");

    private final AuthenticationManager authenticationManager;
    public LoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/general/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("dd");

        String login_id = obtainUsername(request);
        String password = obtainPassword(request);
        System.out.println(login_id);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login_id, password, null); // JWT를 사용할 것임으로 3번째 인자를 사용하지 않을 거임

        return authenticationManager.authenticate(authToken);
    }
    @Override
    @Nullable
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(this.usernameParameter);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication) throws ServletException, IOException {

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(401);
    }
}
