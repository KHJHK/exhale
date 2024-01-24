package com.ssafy.exhale.security;

import com.ssafy.exhale.dto.logicDto.CustomUserDetails;
import com.ssafy.exhale.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collection;
import java.util.Iterator;


public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private String usernameParameter = "login_id";

    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtil;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {

        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {


        String login_id = obtainUsername(request);
        String password = obtainPassword(request);

        System.out.println(login_id+" "+password);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login_id, password, null); // JWT를 사용할 것임으로 3번째 인자를 사용하지 않을 거임
        System.out.println("authToken: "+authToken);
        Authentication au = authenticationManager.authenticate(authToken);
        System.out.println("au: "+au);

        return au;
    }
    @Override
    @Nullable
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(this.usernameParameter);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        System.out.println("success");
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String loginId = customUserDetails.getUsername();
        String nickname = customUserDetails.getNickname();
        String name = customUserDetails.getName();
        int userId = customUserDetails.getMemberId();

        System.out.println("login "+loginId);

        //roll 값
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        String token = jwtUtil.createJwt(name, loginId, userId, nickname, role, 60*60*10L);

        response.addHeader("Authorization", "Bearer "+token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        System.out.println("unsuccess");

        response.setStatus(401);
    }
}
