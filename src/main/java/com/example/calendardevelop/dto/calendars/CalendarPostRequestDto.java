package com.example.calendardevelop.dto.calendars;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class CalendarPostRequestDto {

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 10, message = "제목은 최대 10글자 입니다.")
    private final String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private final String contents;

    public CalendarPostRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
