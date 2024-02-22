package com.example.kotlin2.presentation.controller

import com.example.kotlin2.presentation.dto.ApiResponse
import com.example.kotlin2.presentation.dto.user.LoginRequestDto
import com.example.kotlin2.presentation.dto.user.UserSignDto
import com.example.kotlin2.security.jwt.JwtTokenDto
import com.example.kotlin2.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/auth")
class KotlinApiAuthController(private val authService: AuthService) {

    @PostMapping("/signup")
    fun signUp(@RequestBody userSignDto: UserSignDto?): ResponseEntity<ApiResponse<*>>? {
        val response: ApiResponse<*> = authService.signUp(userSignDto)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginRequestDto?): ResponseEntity<*> {
        val jwtTokenDto: JwtTokenDto = authService.login(loginDto)
        return ResponseEntity.ok(jwtTokenDto.responseDto())
    }
}