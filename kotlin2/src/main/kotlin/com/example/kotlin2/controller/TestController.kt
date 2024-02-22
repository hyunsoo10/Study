package com.example.kotlin2.controller



import com.example.kotlin2.domain.user.User
import com.example.kotlin2.presentation.dto.user.UserDto
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class TestController (){

    @GetMapping("/kotlin")
    fun hello(): String {
        return "안녕 난 코틀린이야"
    }
}