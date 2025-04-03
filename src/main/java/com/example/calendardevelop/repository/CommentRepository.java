package com.example.calendardevelop.repository;

import com.example.calendardevelop.entity.Calendar;
import com.example.calendardevelop.entity.Comment;
import com.example.calendardevelop.global.CustomException;
import com.example.calendardevelop.global.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 요청한 데이터가 없는 경우 404 NOT FOUND
    default Comment findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

}
