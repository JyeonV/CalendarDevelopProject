package com.example.calendardevelop.controller;

import com.example.calendardevelop.dto.calendars.CalendarPostRequestDto;
import com.example.calendardevelop.dto.calendars.CalendarResponseDto;
import com.example.calendardevelop.dto.calendars.CalendarUpdateRequestDto;
import com.example.calendardevelop.service.CalendarService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated // PathVariable 에 validation을 적용하기 위해서 클래스 위에 선언
@RestController
@RequestMapping("/calendars")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @PostMapping("/posts")
    public ResponseEntity<?> save(@RequestBody CalendarPostRequestDto requestDto, BindingResult bindingResult, HttpSession session) {

        if(bindingResult.hasErrors()) {

            String defaultMessage = bindingResult.getFieldError().getDefaultMessage();

            return new ResponseEntity<>(defaultMessage, HttpStatus.BAD_REQUEST);
        }
        // getAttribute를 하면 object로 반환
        long userId = (long) session.getAttribute("userId");

        CalendarResponseDto calendarPostResponseDto = calendarService.save(requestDto.getTitle(), requestDto.getContents(), userId);

        return new ResponseEntity<>(calendarPostResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CalendarResponseDto>> findAll() {

        List<CalendarResponseDto> calendarResponseDtoList = calendarService.findAll();

        return new ResponseEntity<>(calendarResponseDtoList, HttpStatus.OK);
    }

    // bindingresult가 없는데 client에게 직접적인 메세지를 어떻게 보낼것인가 -> exceptionhandler
    @GetMapping("/{id}")
    public ResponseEntity<CalendarResponseDto> findById(@PathVariable @Min(value = 1, message = "ID는 1이상의 값을 입력해주세요.") Long id) {

        CalendarResponseDto calendarResponseDto = calendarService.findById(id);

        return new ResponseEntity<>(calendarResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateTitleAndContents(@PathVariable Long id, @RequestBody @Validated CalendarUpdateRequestDto requestDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {

            String defaultMessage = bindingResult.getFieldError().getDefaultMessage();

            return new ResponseEntity<>(defaultMessage, HttpStatus.BAD_REQUEST);
        }

        calendarService.updateTitleAndContents(id, requestDto.getTitle(), requestDto.getContents(), requestDto.getPassword());

        return new ResponseEntity<>("업데이트 완료", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        calendarService.delete(id);

        return new ResponseEntity<>("삭제 완료",HttpStatus.OK);
    }


}
