package com.example.kotlinspringdemo.controllers

import com.example.kotlinspringdemo.domain.dto.users.UserDetailsDTO
import com.example.kotlinspringdemo.domain.dto.users.UserDetailsRegistrationDTO
import com.example.kotlinspringdemo.services.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService
) {

    @PutMapping("/updateInformation/{id}")
    fun updateUserInformation(
        @PathVariable(name = "id") id: String,
        @RequestBody userDetailsRegistrationDTO: UserDetailsRegistrationDTO
    ): UserDetailsDTO {
        return userService.updateUser(id, userDetailsRegistrationDTO)
    }

    @GetMapping("/getUserById/{id}")
    fun getUser(@PathVariable(name = "id") id: String): UserDetailsDTO {
        return userService.getUser(id)
    }

    @GetMapping("/getAllUsers")
    fun getAllUser(): List<UserDetailsDTO> {
        return userService.getAllUsers()
    }
}