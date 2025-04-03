package com.example.calendardevelop.dto.users;

import com.example.calendardevelop.entity.User;
import lombok.Getter;

@Getter
public class UserGetResponseDto {

    private final long id;

    private final String username;

    private final String email;

    public UserGetResponseDto(long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public static UserGetResponseDto toDto(User user) {
        return new UserGetResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }

}
