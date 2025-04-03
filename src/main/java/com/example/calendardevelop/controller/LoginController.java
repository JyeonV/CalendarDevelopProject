package com.example.calendardevelop.controller;

import com.example.calendardevelop.dto.login.LoginRequestDto;
import com.example.calendardevelop.entity.User;
import com.example.calendardevelop.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Validated LoginRequestDto requestDto, HttpSession session, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {

            String defaultMessage = bindingResult.getFieldError().getDefaultMessage();

            return new ResponseEntity<>(defaultMessage, HttpStatus.BAD_REQUEST);
        }

        // 로그인 이메일, 비밀번호 확인 , user 필드의 다른값을 넣어줄수 있도록 user 형태로 반환
        User user = userService.login(requestDto.getEmail(), requestDto.getPassword());
        // 쿠키 생성
        session.setAttribute("userId", user.getId());

        return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {

        session.invalidate();

        return new ResponseEntity<>("로그아웃 성공", HttpStatus.OK);
    }
}
