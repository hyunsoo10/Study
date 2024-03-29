package com.example.kotlin2.presentation.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Getter
public class UserSignDto {
    private String userId;
    private String password;
    private String name;
    private String email;
    private int age;

    public UserSignDto(String userId, String password, String name, String email, int age) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }
}
