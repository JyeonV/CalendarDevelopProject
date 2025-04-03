package com.example.calendardevelop.dto.calendars;


import com.example.calendardevelop.dto.users.UserGetResponseDto;
import com.example.calendardevelop.entity.Calendar;
import com.example.calendardevelop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CalendarResponseDto {

    private final long id;

    private final String title;

    private final String contents;

    private final Long userId;

    public static CalendarResponseDto toDto(Calendar calendar) {
        return new CalendarResponseDto(calendar.getId(), calendar.getTitle(), calendar.getContents(), calendar.getUser().getId());
    }

}
