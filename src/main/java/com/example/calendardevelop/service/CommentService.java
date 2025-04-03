package com.example.calendardevelop.service;

import com.example.calendardevelop.dto.calendars.CalendarResponseDto;
import com.example.calendardevelop.dto.comment.CommentResponseDto;
import com.example.calendardevelop.entity.Calendar;
import com.example.calendardevelop.entity.Comment;
import com.example.calendardevelop.entity.User;
import com.example.calendardevelop.global.CustomException;
import com.example.calendardevelop.global.ErrorCode;
import com.example.calendardevelop.repository.CalendarRepository;
import com.example.calendardevelop.repository.CommentRepository;
import com.example.calendardevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final CalendarRepository calendarRepository;


    public void postComment(String contents, long userId, long calendarId) {

        User user = userRepository.findByIdOrElseThrow(userId);

        Calendar calendar = calendarRepository.findByIdOrElseThrow(calendarId);

        Comment comment = new Comment(contents, user, calendar);

        commentRepository.save(comment);

    }

    public CommentResponseDto findById(Long id) {

        Comment comment = commentRepository.findByIdOrElseThrow(id);

        return new CommentResponseDto(comment.getContents(), comment.getUser().getUsername());

    }

    public void updateCommentContents(Long id, String contents, long userId) {

        Comment comment = commentRepository.findByIdOrElseThrow(id);

        if(userId != comment.getUser().getId()) {
            throw new CustomException(ErrorCode.INVALID_ID_PW);
        }

        comment.updateCommentContents(contents);
    }

    public void delete(Long id) {

        Comment comment = commentRepository.findByIdOrElseThrow(id);

        commentRepository.delete(comment);
    }
}
