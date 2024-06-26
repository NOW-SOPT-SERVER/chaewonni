package org.sopt.practice.common.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sopt.practice.common.auth.dto.CustomUserDetails;
import org.sopt.practice.common.auth.service.AuthenticationService;
import org.sopt.practice.common.dto.ErrorMessage;
import org.sopt.practice.common.dto.ErrorResponse;
import org.sopt.practice.common.dto.SuccessMessage;
import org.sopt.practice.common.dto.SuccessStatusResponse;
import org.sopt.practice.service.dto.UserJoinResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;

    public LoginFilter(AuthenticationManager authenticationManager, AuthenticationService authenticationService) {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
        super.setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl("/api/v1/members/login");  // 추가 설정
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        //클라이언트 요청에서 username, password 추출
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        System.out.println(username);

        //스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 함
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        //token에 담아 검증을 위한 AuthenticationManager(검증 담당)로 전달
        return authenticationManager.authenticate(authToken);
    }

    //로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {

        //특정한 user 가지고 오기
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        UserJoinResponse dto = authenticationService.handleLoginSuccess(customUserDetails);

        // Response 준비
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        // JSON 형태로 응답 본문에 쓰기
        try {
            PrintWriter out = response.getWriter();
            ResponseEntity<SuccessStatusResponse<UserJoinResponse>> apiResponse = ResponseEntity.status(HttpStatus.OK).body(SuccessStatusResponse.of(SuccessMessage.MEMBER_LOGIN_SUCCESS, dto));
            out.print(new ObjectMapper().writeValueAsString(apiResponse));
            out.flush();
        } catch (IOException e) {
            // 예외 로깅 또는 추가 처리
            e.printStackTrace();  // 서버 로그에 예외를 기록 (실제 운영 환경에서는 더 세밀한 예외 처리 로직 필요)
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);  // 클라이언트에게 500 에러 응답
//            response.getWriter().print("{\"error\":\"Internal server error\"}");  // 에러 메시지를 JSON 형태로 반환
        }
    }

    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {

        // Response 준비
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        try {
            PrintWriter out = response.getWriter();
            ResponseEntity<ErrorResponse> apiResponse = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse.of(ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION));
            out.print(new ObjectMapper().writeValueAsString(apiResponse));
            out.flush();
        } catch (IOException e) {
            // 예외 로깅 또는 추가 처리
            e.printStackTrace();  // 서버 로그에 예외를 기록 (실제 운영 환경에서는 더 세밀한 예외 처리 로직 필요)
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);  // 클라이언트에게 500 에러 응답
//            response.getWriter().print("{\"error\":\"Internal server error\"}");  // 에러 메시지를 JSON 형태로 반환
        }
    }
}
