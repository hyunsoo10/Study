package com.example.kotlin2.presentation.dto.user;

import com.example.kotlin2.domain.user.User;

public class UserResponseDto {
    private String userId;
    private String name;
    private String email;

    public UserResponseDto(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getUserId(),
                user.getName(),
                user.getEmail()
        );
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
