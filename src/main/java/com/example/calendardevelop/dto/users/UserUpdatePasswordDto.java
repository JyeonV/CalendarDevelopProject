package com.example.calendardevelop.dto.users;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserUpdatePasswordDto {

    @NotBlank(message = "기존 비밀번호를 입력하세요.")
    private final String oldPassword;

    @NotBlank(message = "새로 바꿀 비밀번호를 입력하세요.")
    private final String newPassword;

    public UserUpdatePasswordDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
