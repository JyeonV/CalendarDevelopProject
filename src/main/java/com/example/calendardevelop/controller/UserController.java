package com.example.calendardevelop.controller;

import com.example.calendardevelop.dto.users.UserGetResponseDto;
import com.example.calendardevelop.dto.users.UserPostRequestDto;
import com.example.calendardevelop.dto.users.UserPostResponseDto;
import com.example.calendardevelop.dto.users.UserUpdatePasswordDto;
import com.example.calendardevelop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService; // 생성자 주입

    // user 등록
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Validated UserPostRequestDto requestDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {

            String defaultMessage = bindingResult.getFieldError().getDefaultMessage();

            return new ResponseEntity<>(defaultMessage, HttpStatus.BAD_REQUEST);
        }

        UserPostResponseDto userPostResponseDto = userService.signUp(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());

        return new ResponseEntity<>(userPostResponseDto, HttpStatus.OK);
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<UserGetResponseDto>> findAll() {

        List<UserGetResponseDto> userGetResponseDtoList = userService.findAll();

        return new ResponseEntity<>(userGetResponseDtoList, HttpStatus.OK);
    }

    // id로 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserGetResponseDto> findById(@PathVariable Long id) {

        UserGetResponseDto userGetResponseDto = userService.findById(id);

        return new ResponseEntity<>(userGetResponseDto, HttpStatus.OK);
    }

    // 비밀번호 변경(id)
    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable Long id, @RequestBody @Validated UserUpdatePasswordDto requestDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {

            String defaultMessage = bindingResult.getFieldError().getDefaultMessage();

            return new ResponseEntity<>(defaultMessage, HttpStatus.BAD_REQUEST);
        }

        userService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // user 삭제(id)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
