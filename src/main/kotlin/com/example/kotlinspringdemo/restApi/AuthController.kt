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

    @Operation(
        summary = "Login User",
        description = """
            login User via Username and password
            
            don't need Authentication for this service.            
            Http
                Body:
                    <loginDto>
                        <username></username>
                        <password></password>
                    </loginDto>
        """
    )
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "User login")])
    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto): LoginResponseDto {
        val user = userService.getUserByUsername(loginDto.username)
        val token = tokenService.createToken(user)
        return LoginResponseDto(token)
    }

    @Operation(
        summary = "Register User",
        description = """
            Register User
            
            don't need Authentication for this service.            
            Http
                Body:
                    <UserDetailsRegistrationDTO>
                        <name></name>
                        <userName></userName>
                        <password></password>
                        <emailAddress></emailAddress>
                    </UserDetailsRegistrationDTO>
        """
    )
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "User login")])
    @PostMapping("/register")
    fun userRegistration(@RequestBody userDetailsRegistrationDTO: UserDetailsRegistrationDTO): LoginResponseDto {
        val savedUser = userService.createUser(userDetailsRegistrationDTO)
        val token = tokenService.createToken(savedUser)
        return LoginResponseDto(token)
    }

}