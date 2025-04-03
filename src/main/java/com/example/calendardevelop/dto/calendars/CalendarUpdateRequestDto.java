package com.example.calendardevelop.dto.calendars;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CalendarUpdateRequestDto {

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 10, message = "제목은 최대 10글자 입니다.")
    private final String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private final String contents;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private final String password;


    public CalendarUpdateRequestDto(String title, String contents, String password) {
        this.title = title;
        this.contents = contents;
        this.password = password;
    }
}
