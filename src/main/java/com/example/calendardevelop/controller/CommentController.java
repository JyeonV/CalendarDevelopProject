package com.example.calendardevelop.controller;

import com.example.calendardevelop.dto.calendars.CalendarResponseDto;
import com.example.calendardevelop.dto.calendars.CalendarUpdateRequestDto;
import com.example.calendardevelop.dto.comment.CommentRequestDto;
import com.example.calendardevelop.dto.comment.CommentResponseDto;
import com.example.calendardevelop.service.CommentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/calendars/{calendarId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> postComment(@PathVariable Long calendarId, @RequestBody CommentRequestDto request, HttpSession session) {

        long userId = (long) session.getAttribute("userId");

        commentService.postComment(request.getContents(), userId, calendarId);

        return new ResponseEntity<>("댓글 작성 완료", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> findById(@PathVariable @Min(value = 1, message = "ID는 1이상의 값을 입력해주세요.") Long id) {

        CommentResponseDto commentResponseDto = commentService.findById(id);

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateCommentContents(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpSession session) {

        long userId = (long) session.getAttribute("userId");

        commentService.updateCommentContents(id, requestDto.getContents(), userId);

        return new ResponseEntity<>("업데이트 완료", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        commentService.delete(id);

        return new ResponseEntity<>("삭제 완료",HttpStatus.OK);
    }
}
