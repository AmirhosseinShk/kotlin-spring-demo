package com.example.kotlinspringdemo.repository

import com.example.kotlinspringdemo.domain.users.UserDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepo : JpaRepository<UserDetails, String> {

    fun findByUserName(username: String): Optional<UserDetails>
}