package com.example.calendardevelop.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {

        super(errorCode.getMessage());
        this.errorCode = errorCode;

    }

}

