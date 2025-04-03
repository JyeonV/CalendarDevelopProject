package com.example.calendardevelop.dto.users;

import lombok.Getter;

@Getter
public class UserPostResponseDto {

    private final String username;

    private final String email;

    public UserPostResponseDto(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
