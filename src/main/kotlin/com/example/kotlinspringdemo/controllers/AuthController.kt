package com.example.kotlinspringdemo.controllers

import com.example.kotlinspringdemo.controllers.dto.auth.LoginDto
import com.example.kotlinspringdemo.controllers.dto.auth.LoginResponseDto
import com.example.kotlinspringdemo.controllers.dto.users.UserDetailsRegistrationDTO
import com.example.kotlinspringdemo.services.TokenService
import com.example.kotlinspringdemo.services.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/authentication")
class AuthController(
    private val tokenService: TokenService,
    private val userService: UserService,
) {

    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto): LoginResponseDto {
        val user = userService.getUserByUsername(loginDto.username)
        val token = tokenService.createToken(user)
        return LoginResponseDto(token)
    }

    @PostMapping("/register")
    fun userRegistration(@RequestBody userDetailsRegistrationDTO: UserDetailsRegistrationDTO): LoginResponseDto {
        val savedUser = userService.createUser(userDetailsRegistrationDTO)
        val token = tokenService.createToken(savedUser)
        return LoginResponseDto(token)
    }

}