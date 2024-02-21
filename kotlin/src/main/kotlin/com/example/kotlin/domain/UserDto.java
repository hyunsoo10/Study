package com.example.kotlin.domain;

import com.example.kotlin.domain.User;
import org.springframework.stereotype.Component;

@Component
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
