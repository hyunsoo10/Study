package com.example.kotlin2.java;

import com.example.kotlin2.domain.user.Role;
import com.example.kotlin2.domain.user.User;
import com.example.kotlin2.presentation.dto.user.UserSignDto;
import com.example.kotlin2.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JavaTest {

    @Autowired
    UserService userService;

    @Test
    @DisplayName("유저 테스트")
    void user() throws Exception{

        //given
        UserSignDto userSignDto1 = new UserSignDto("test99", "1234", "테스트", "test@test.com", 10);

        //when
        userService.signUp(userSignDto1);

        //then
        UserSignDto userSignDto2 = new UserSignDto("test99", "123234", "테스트2", "test2@test.com", 10);
        Assertions.assertThrows(IllegalStateException.class, ()-> userService.signUp(userSignDto2));

    }
}
