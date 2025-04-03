package com.example.calendardevelop.filter;

import com.example.calendardevelop.global.CustomException;
import com.example.calendardevelop.global.ErrorCode;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/users/signup","/login","/logout"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 기능을 더 많이 쓸수 있도록 다운캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();

        // 요청에서 가져온 URI 가 WHITE_LIST 에 등록이 안되어 있다면
        if(!isWhiteList(requestURI)) {
            // false를 안해주면 로그인을 안해도 session을 생성, false -> session이 없다면 null 반환
            HttpSession session = httpRequest.getSession(false);
            if (session == null || session.getAttribute("userId") == null) {
                throw new CustomException(ErrorCode.INVALID_ID_PW);
            }
        }

        // 쿠키로 썼을때, 근데 이것도 작동은한다 -> session도 결국 쿠키로 JSESSIONID를 사용하기 때문에
//        if(!isWhiteList(requestURI)) {
//            Cookie[] cookie = httpRequest.getCookies(); // getCookie 는 cookie 가 없을시 null
//            if(cookie == null) {
//                throw new RuntimeException("로그인 해주세요");
//            }
//        }

        chain.doFilter(request, response);

    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }


}
