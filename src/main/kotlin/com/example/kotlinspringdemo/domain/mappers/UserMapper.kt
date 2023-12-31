package com.example.kotlinspringdemo.domain.mappers

import com.example.kotlinspringdemo.domain.users.USERROLE
import com.example.kotlinspringdemo.domain.users.UserDetails
import com.example.kotlinspringdemo.domain.dto.users.UserDetailsDTO
import com.example.kotlinspringdemo.domain.dto.users.UserDetailsRegistrationDTO
import com.example.kotlinspringdemo.repository.UserRepo
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserMapper(
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {
    fun userDetailsToUserDetailsDTO(userDetails: UserDetails): UserDetailsDTO {
        return UserDetailsDTO(
            userDetails.id!!,
            userDetails.name,
            userDetails.userName,
            userDetails.emailAddress,
            userDetails.registrationDate,
            userDetails.userRole
        )
    }

    fun userDetailsRegistrationDTOToUserDetails(userDetailsRegistrationDTO: UserDetailsRegistrationDTO): UserDetails {
        val registrationDate = Date()
        val hashedPassword = bCryptPasswordEncoder.encode(userDetailsRegistrationDTO.password)
        return UserDetails(
            null,
            userDetailsRegistrationDTO.name,
            userDetailsRegistrationDTO.userName,
            hashedPassword,
            userDetailsRegistrationDTO.emailAddress,
            registrationDate,
            USERROLE.USER
        )
    }

    fun hashedUserDetailsPassword(password: String): String = bCryptPasswordEncoder.encode(password)
}