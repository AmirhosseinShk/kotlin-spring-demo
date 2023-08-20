package com.example.kotlinspringdemo.dataModel.users

data class UserDetailsRegistrationDTO(
    val name: String,
    val userName: String,
    val password: String,
    val emailAddress: String,
)