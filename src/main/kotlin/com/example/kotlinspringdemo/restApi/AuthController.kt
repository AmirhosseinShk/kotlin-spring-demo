package com.example.kotlinspringdemo.restApi

import com.example.kotlinspringdemo.dataModel.auth.LoginDto
import com.example.kotlinspringdemo.dataModel.auth.LoginResponseDto
import com.example.kotlinspringdemo.dataModel.users.UserDetailsRegistrationDTO
import com.example.kotlinspringdemo.services.TokenService
import com.example.kotlinspringdemo.services.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
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