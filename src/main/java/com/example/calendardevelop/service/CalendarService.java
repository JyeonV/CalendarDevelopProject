package com.example.calendardevelop.service;

import com.example.calendardevelop.dto.calendars.CalendarResponseDto;
import com.example.calendardevelop.dto.users.UserGetResponseDto;
import com.example.calendardevelop.entity.Calendar;
import com.example.calendardevelop.entity.User;
import com.example.calendardevelop.global.CustomException;
import com.example.calendardevelop.global.ErrorCode;
import com.example.calendardevelop.repository.CalendarRepository;
import com.example.calendardevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;

    private final UserRepository userRepository;

    public CalendarResponseDto save(String title, String contents, long userId) {

        User user = userRepository.findByIdOrElseThrow(userId);

        Calendar calendar = new Calendar(title, contents, user);

        Calendar calendarSaved = calendarRepository.save(calendar);

        return new CalendarResponseDto(calendarSaved.getId(), calendarSaved.getTitle(), calendarSaved.getContents(), calendarSaved.getUser().getId());
    }


    public List<CalendarResponseDto> findAll() {

        return calendarRepository.findAll().stream().map(CalendarResponseDto::toDto).toList();
    }

    public CalendarResponseDto findById(Long id) {

        Calendar calendar = calendarRepository.findByIdOrElseThrow(id);

        return new CalendarResponseDto(calendar.getId(), calendar.getTitle(), calendar.getContents(), calendar.getUser().getId());
    }

    @Transactional
    public void updateTitleAndContents(Long id, String title, String contents, String password) {

        Calendar calendar = calendarRepository.findByIdOrElseThrow(id);

        if(!password.equals(calendar.getUser().getPassword())) {
            throw new CustomException(ErrorCode.INVALID_ID_PW);
        }

        calendar.updateTitleAndContents(title, contents);
    }

    public void delete(Long id) {

        Calendar calendar = calendarRepository.findByIdOrElseThrow(id);

        calendarRepository.delete(calendar);
    }

}
