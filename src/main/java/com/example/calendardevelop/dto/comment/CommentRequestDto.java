package com.example.calendardevelop.dto.comment;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    private final String contents;

    public CommentRequestDto(String contents) {
        this.contents = contents;
    }
}
