package com.example.kotlin.controller



import com.example.kotlin.domain.User
import com.example.kotlin.domain.UserDto
import com.example.kotlin.service.UserService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class TestController (val userService: UserService){

    @GetMapping("/kotlin")
    fun hello(): String {
        return "안녕 난 코틀린이야"
    }

    @GetMapping("/java")
    fun java(@RequestParam("name") name: String): UserDto {
        val user = User()
        println(user)
        user.name = name
        val userDto = UserDto(user)
        return userDto
    }

    @GetMapping("/{seq}")
    fun user(@PathVariable seq: Long): User {
//        val userService = UserService(userRepository())
        val user: User = userService.getUserBySeq(seq);

        return user;
    }

//    fun userRepository(): UserRepository = UserRepositoryImpl()
}