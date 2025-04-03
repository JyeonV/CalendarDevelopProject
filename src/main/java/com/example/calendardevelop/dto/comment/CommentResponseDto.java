package com.example.calendardevelop.dto.comment;

import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final String contents;

    private final String username;


    public CommentResponseDto(String contents, String username) {
        this.contents = contents;
        this.username = username;
    }
}
