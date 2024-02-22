package com.example.kotlin2.presentation.dto.user;

import com.example.kotlin2.domain.user.User;
import org.springframework.stereotype.Component;

public class UserDto {

    private String username;
    private int userAge;

    public UserDto(User user) {
        this.username = user.getName();
        this.userAge = user.getAge();
    }


    public String getUsername() {
        return username;
    }

    public int getUserAge() {
        return userAge;
    }
}
