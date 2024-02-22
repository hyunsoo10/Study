package com.example.kotlin2.presentation.controller

import com.example.kotlin2.presentation.dto.ApiResponse
import com.example.kotlin2.presentation.dto.user.LoginRequestDto
import com.example.kotlin2.presentation.dto.user.UserDto
import com.example.kotlin2.presentation.dto.user.UserResponseDto
import com.example.kotlin2.presentation.dto.user.UserSignDto
import com.example.kotlin2.security.jwt.JwtTokenDto
import com.example.kotlin2.service.AuthService
import com.example.kotlin2.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class KotlinUserApiController(private val userService: UserService, private val authService: AuthService) {

    @GetMapping("/{seq}")
    fun user(@PathVariable seq: Long): UserResponseDto {
        val user: UserResponseDto = userService.getUserBySeq(seq)
        return user;
    }

    @PostMapping("/duplicate")
    fun duplicate(@RequestParam userId: String): ResponseEntity<ApiResponse<*>>?{
        val response: ApiResponse<*> = userService.idDuplicateCheck(userId)
        return ResponseEntity.ok(response)
    }


    @PostMapping("/signup")
    fun signUp(@RequestBody userSignDto: UserSignDto?): ResponseEntity<ApiResponse<*>>? {
        val response: ApiResponse<*> = userService.signUp(userSignDto)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/login")
    fun passwordCheck(@RequestBody userDto: UserDto): ResponseEntity<*> {
        val isLogin: Boolean = userService.validatePassword(userDto)
        return ResponseEntity.ok(isLogin)
    }


}