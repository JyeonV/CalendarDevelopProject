package com.example.calendardevelop.repository;

import com.example.calendardevelop.entity.Calendar;
import com.example.calendardevelop.entity.User;
import com.example.calendardevelop.global.CustomException;
import com.example.calendardevelop.global.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    default Calendar findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
