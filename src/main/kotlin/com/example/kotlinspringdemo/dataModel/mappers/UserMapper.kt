package com.example.kotlinspringdemo.dataModel.mappers

import com.example.kotlinspringdemo.dataModel.users.USERROLE
import com.example.kotlinspringdemo.dataModel.users.UserDetails
import com.example.kotlinspringdemo.controllers.dto.users.UserDetailsDTO
import com.example.kotlinspringdemo.controllers.dto.users.UserDetailsRegistrationDTO
import com.example.kotlinspringdemo.repository.UserRepo
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserMapper(
    private val userRepo: UserRepo
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
        return UserDetails(
            null,
            userDetailsRegistrationDTO.name,
            userDetailsRegistrationDTO.userName,
            userDetailsRegistrationDTO.password,
            userDetailsRegistrationDTO.emailAddress,
            registrationDate,
            USERROLE.USER
        )
    }

}