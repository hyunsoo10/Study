package com.example.kotlin2.presentation.controller

import com.example.kotlin2.domain.user.User
import com.example.kotlin2.presentation.dto.user.UserSignDto
import com.example.kotlin2.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class KotlinUserApiController (private val userService: UserService){

    @GetMapping("/{seq}")
    fun user(@PathVariable seq: Long): User {
        val user: User = userService.getUserBySeq(seq)
        return user;
    }

    @PostMapping("/signup")
    fun signUp(@RequestBody userSignDto: UserSignDto?): ResponseEntity<*>? {
        userService.signUp(userSignDto)
        return ResponseEntity.status(HttpStatus.OK).build<Any>()
    }
}