package com.example.kotlinspringdemo.domain.dto.users

data class UserDetailsRegistrationDTO(
    val name: String,
    val userName: String,
    val password: String,
    val emailAddress: String,
)