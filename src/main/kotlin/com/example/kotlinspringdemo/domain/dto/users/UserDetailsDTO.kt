package com.example.kotlinspringdemo.domain.dto.users

import com.example.kotlinspringdemo.domain.users.USERROLE
import java.util.*

data class UserDetailsDTO(
    val id: String?,
    val name: String?,
    val userName: String?,
    val emailAddress: String?,
    val registrationDate: Date?,
    val userRole: USERROLE?
)