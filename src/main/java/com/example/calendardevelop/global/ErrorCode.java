package com.example.calendardevelop.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_ID_PW(401, "UNAUTHORIZED", "Invalid ID/PW"),
    USER_NOT_FOUND(404, "NOT_FOUND","User Not Found"),
    CALENDAR_NOT_FOUND(404, "NOT_FOUND","Calendar Not Found");

    private final int Status;

    private final String error;

    private final String message;
}


//1. userRepository – get – id로 조회했을 때 db에 없는 경우  -> 404, B

//2. userRepository – get – email로 조회했을 때 db에 없는 경우  -> 401(로그인 기능임) A

//3. userService – update - pw수정할 때 기존이랑 틀릴 경우 -> 401 A

//4. userService – post - 로그인할 때 비밀번호 틀릴 경우 -> 401 A

//5. calendarService – update – 일정 수정할 때 비밀번호 틀린 경우 -> 401 A

//6. calendarRepository – get – 일정 조회할 때 없는 경우 -> 404 C

//7. filter – 세션 정보 없을 때 -> 401