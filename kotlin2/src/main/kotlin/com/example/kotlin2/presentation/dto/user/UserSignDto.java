package com.example.kotlin2.presentation.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserSignDto {
    private String userId;
    private String password;
    private String name;
    private String email;

}
