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

    @GetMapping("/java")
    fun java(@RequestParam("name") name: String): UserDto {
        val user = User()
        println(user)
        user.name = name
        val userDto = UserDto(user)
        return userDto
    }

//    @GetMapping("/{seq}")
//    fun user(@PathVariable seq: Long): User {
//        val memberService = UserService(memberRepository())
//        val member: User = memberService.getMemberBySeq(seq)
//
//        return member;
//    }
//
//    fun memberRepository(): UserRepository = UserRepositoryImpl()
}