package com.example.kotlinspringdemo.dataModel.users

import java.util.*

data class UserDetailsDTO(
    val id: String,
    val name: String,
    val userName: String,
    val emailAddress: String,
    val registrationDate: Date,
    val userRole: USERROLE
)