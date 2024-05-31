package org.sopt.daangnMarket.common.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.sopt.daangnMarket.common.auth.UserAuthentication;
import org.sopt.daangnMarket.common.auth.dto.CustomUserDetails;
import org.sopt.daangnMarket.common.jwt.JwtTokenProvider;
import org.sopt.daangnMarket.common.auth.service.AuthenticationService;
import org.sopt.daangnMarket.dto.response.member.MemberJoinResponse;
import org.sopt.daangnMarket.util.ApiResponse;
import org.sopt.daangnMarket.util.dto.ErrorMessage;
import org.sopt.daangnMarket.util.dto.SuccessMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;
    private final JwtTokenProvider jwtTokenProvider;

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

        // UserAuthentication 객체 생성
        UserAuthentication userAuthentication = UserAuthentication.createUserAuthentication(customUserDetails);

//        //username을 authentication 객체에서로부터 뽑아옴
//        String username = customUserDetails.getUsername();
//
//        //role을 authentication 객체에서로부터 뽑아옴
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
//        GrantedAuthority auth = iterator.next();
//
//        String role = auth.getAuthority();

        MemberJoinResponse dto = authenticationService.handleLoginSuccess(userAuthentication);

        // Response 준비
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        // JSON 형태로 응답 본문에 쓰기
        try {
            PrintWriter out = response.getWriter();
            ApiResponse<MemberJoinResponse> apiResponse = new ApiResponse<>(true, SuccessMessage.MEMBER_LOGIN_SUCCESS.getStatus(), SuccessMessage.MEMBER_LOGIN_SUCCESS.getMessage(), dto);
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
            ApiResponse<String> apiResponse = new ApiResponse<>(false, ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION.getStatus(), ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION.getMessage(), null);
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
