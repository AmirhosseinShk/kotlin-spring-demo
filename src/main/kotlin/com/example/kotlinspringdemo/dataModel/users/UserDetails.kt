package com.example.kotlinspringdemo.dataModel.users

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.Date

@Entity(name = "users")
data class UserDetails(
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id val id: String? = null,
    val name: String,
    val userName: String,
    val password: String,
    val emailAddress: String,
    val registrationDate: Date,
    val userRole: USERROLE
)

enum class USERROLE {
    USER,
}