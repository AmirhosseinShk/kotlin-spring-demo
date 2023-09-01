package com.example.kotlinspringdemo.controllers

import com.example.kotlinspringdemo.domain.dto.auth.LoginDTO
import com.example.kotlinspringdemo.domain.dto.auth.LoginResponseDTO
import com.example.kotlinspringdemo.domain.dto.users.UserDetailsRegistrationDTO
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
    private val userService: UserService
) {

    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDTO): LoginResponseDTO {
        val user = userService.getUserByUsernameAndCheckedPassword(loginDto.username, loginDto.password)
        val token = tokenService.createToken(user)
        return LoginResponseDTO(token)
    }

    @PostMapping("/register")
    fun userRegistration(@RequestBody userDetailsRegistrationDTO: UserDetailsRegistrationDTO): LoginResponseDTO {
        val savedUser = userService.createUser(userDetailsRegistrationDTO)
        val token = tokenService.createToken(savedUser)
        return LoginResponseDTO(token)
    }

}