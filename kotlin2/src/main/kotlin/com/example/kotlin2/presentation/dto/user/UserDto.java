package com.example.kotlin2.presentation.dto.user;

import com.example.kotlin2.domain.user.User;
import org.springframework.stereotype.Component;

public class UserDto {

    private String userId;
    private String password;

    public UserDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}
