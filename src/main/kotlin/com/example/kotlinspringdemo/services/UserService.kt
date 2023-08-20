package com.example.kotlinspringdemo.services

import com.example.kotlinspringdemo.dataModel.mappers.UserMapper
import com.example.kotlinspringdemo.dataModel.users.UserDetailsDTO
import com.example.kotlinspringdemo.dataModel.users.UserDetailsRegistrationDTO
import com.example.kotlinspringdemo.exceptions.DuplicatedUsernameException
import com.example.kotlinspringdemo.exceptions.NotImplementedException
import com.example.kotlinspringdemo.exceptions.UserNotFoundException
import com.example.kotlinspringdemo.repository.UserRepo
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepo: UserRepo,
    private val userMapper: UserMapper
) {

    fun createUser(userDetailsRegistrationDTO: UserDetailsRegistrationDTO): UserDetailsDTO {
        val userByUserName = userRepo.findByUserName(userDetailsRegistrationDTO.userName)
        if (userByUserName.isPresent) {
            throw DuplicatedUsernameException("this Username registered before!")
        } else {
            val userDetails = userMapper.userDetailsRegistrationDTOToUserDetails(userDetailsRegistrationDTO)
            userRepo.save(userDetails)
            return userMapper.userDetailsToUserDetailsDTO(userDetails)
        }
    }

    fun getUser(id: String): UserDetailsDTO {
        val userDetails = userRepo.findById(id)
        if (userDetails.isPresent)
            return userMapper.userDetailsToUserDetailsDTO(userDetails.get())
        else
            throw UserNotFoundException("User id not found")
    }

    fun getAllUsers(): List<UserDetailsDTO> {
        return userRepo.findAll().stream().map(userMapper::userDetailsToUserDetailsDTO).toList()
    }

    fun updateUser(): UserDetailsDTO {
        throw NotImplementedException("Update User not implemented Yet!")
    }

    fun getUserByUsername(username: String): UserDetailsDTO {
        val userDetails = userRepo.findByUserName(username)
        if (userDetails.isPresent)
            return userMapper.userDetailsToUserDetailsDTO(userDetails.get())
        else
            throw UserNotFoundException("User id not found")
    }
}